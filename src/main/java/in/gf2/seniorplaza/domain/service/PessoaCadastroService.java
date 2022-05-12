package in.gf2.seniorplaza.domain.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import in.gf2.seniorplaza.Utils;
import in.gf2.seniorplaza.domain.exception.CPFInvalidoException;
import in.gf2.seniorplaza.domain.exception.PessoaExistenteException;
import in.gf2.seniorplaza.domain.exception.LockedReservaException;
import in.gf2.seniorplaza.domain.model.Pessoa;
import in.gf2.seniorplaza.domain.model.Reserva;
import in.gf2.seniorplaza.domain.repository.PessoaRepository;
import in.gf2.seniorplaza.domain.repository.ReservaRepository;

@Service
@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
public class PessoaCadastroService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	public Pessoa savePessoa(Pessoa pessoa) {
		return savePessoa(pessoa, false);
	}
	
	public Pessoa savePessoa(Pessoa pessoa, boolean atualizando) {
		var data = new Timestamp(new Date().getTime());
		var input = pessoaRepository.findByCpf(pessoa.getCpf());
		if(input != null && ((atualizando && input.getId().compareTo(pessoa.getId()) != 0) || !atualizando)) {
			throw new PessoaExistenteException("CPF");
		}
		if(!Utils.isCPFValido(pessoa.getCpf())) {
			throw new CPFInvalidoException();
		}
		pessoa.setCpf(pessoa.getCpf().replace(".", "").replace("/", ""));
		pessoa.setDataCadastro(!atualizando ? data : input.getDataCadastro());
		pessoa.setDataAlteracao(data);
		return pessoaRepository.save(pessoa);
	}
	
	public void deletePessoa(BigDecimal id) {
		Reserva reserva = reservaRepository.findByPessoaId(id);
		if(reserva != null)
			throw new LockedReservaException(reserva.getId().toString());
		pessoaRepository.deleteById(id);
	}
}
