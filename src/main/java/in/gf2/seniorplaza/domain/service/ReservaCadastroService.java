package in.gf2.seniorplaza.domain.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.gf2.seniorplaza.Utils;
import in.gf2.seniorplaza.api.model.CheckinInput;
import in.gf2.seniorplaza.api.model.Checkout;
import in.gf2.seniorplaza.api.model.ReservaInput;
import in.gf2.seniorplaza.domain.exception.PessoaInexistenteException;
import in.gf2.seniorplaza.domain.exception.ReservaExistenteException;
import in.gf2.seniorplaza.domain.exception.ReservaInexistenteException;
import in.gf2.seniorplaza.domain.model.Reserva;
import in.gf2.seniorplaza.domain.repository.PessoaRepository;
import in.gf2.seniorplaza.domain.repository.ReservaRepository;

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class ReservaCadastroService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private CheckoutService checkinService;
	
	
	public Reserva saveReserva(ReservaInput reservaInput) {
		return saveReserva(reservaInput, null, false);
	}
	
	public Reserva saveReserva(ReservaInput reservaInput, BigDecimal reservaId, boolean atualizando) {
		var reserva = new Reserva();
		reserva.setId(reservaId);
		reserva.setQuarto(reservaInput.getQuarto());
		reserva.setPessoaId(reservaInput.getPessoaId());
		reserva.setDataReserva(Utils.timestampFromString(reservaInput.getDataReserva()));
		reserva.setVagaGaragem(reservaInput.isVagaGaragem());
		var dataCadastro = new Timestamp(new Date().getTime());
		var dataAlteracao = dataCadastro;
		var oInput = reservaRepository.findByPessoaId(reserva.getPessoaId());
		Reserva input = null;
		if(oInput.isPresent()) {
			input = oInput.get();
			if((atualizando && input.getId().compareTo(reserva.getId()) != 0 || !atualizando)) {
				throw new ReservaExistenteException(input.getId().toString());
			}
			dataCadastro = input.getDataCadastro();
		}
		reserva.setDataCadastro(dataCadastro);
		reserva.setDataAlteracao(dataAlteracao);
		return reservaRepository.save(reserva);
	}
	
	public Reserva checkin(BigDecimal reservaId, CheckinInput checkinInput) {
		var reserva = reservaRepository.findById(reservaId);
		if(!reserva.isPresent()) {
			throw new ReservaInexistenteException(reservaId.toString());
		}
		var pessoa = pessoaRepository.findById(checkinInput.getPessoaId());
		if(!pessoa.isPresent()) {
			throw new PessoaInexistenteException(checkinInput.getPessoaId().toString());
		}
		var reservation = reserva.get();
		var data = new Timestamp(new Date().getTime());
		var dataChegada = data;
		if(checkinInput.getDataChegada() != null && !"".equals(checkinInput.getDataChegada())) {
			dataChegada = Utils.timestampFromString(checkinInput.getDataChegada());
		}
		reservation.setDataAlteracao(data);
		reservation.setDataChegada(dataChegada);
		reservation.setPessoaId(reservaId);
		return reservaRepository.save(reservation);
	}
	
	public Checkout doCheckout(BigDecimal reservaId) {
		var reserva = reservaRepository.findById(reservaId);
		if(!reserva.isPresent()) {
			throw new ReservaInexistenteException(reservaId.toString());
		}
		var reservation = reserva.get();
		var data = new Timestamp(new Date().getTime());
		reservation.setDataSaida(data);
		reservation.setDataAlteracao(data);
		reservaRepository.save(reservation);
		return checkinService.checkout(reserva.get());
	}
}
