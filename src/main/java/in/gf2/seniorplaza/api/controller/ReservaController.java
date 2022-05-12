package in.gf2.seniorplaza.api.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.gf2.seniorplaza.api.model.Checkout;
import in.gf2.seniorplaza.api.model.CheckinInput;
import in.gf2.seniorplaza.api.model.ReservaInput;
import in.gf2.seniorplaza.domain.model.Reserva;
import in.gf2.seniorplaza.domain.repository.ReservaRepository;
import in.gf2.seniorplaza.domain.service.ReservaCadastroService;

@RestController
@CrossOrigin
@RequestMapping("/reservas")
public class ReservaController extends PlazaController {
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ReservaCadastroService reservaService;
	
	@GetMapping("/page/{page}")
	public List<Reserva> getAllReservaPaged(@PathVariable Integer page) {
		List<Reserva> list = new ArrayList<>();
		Pageable paging = PageRequest.of(page, PAGE_SIZE);
		Page<Reserva> paged = reservaRepository.findAll(paging);
		list = paged.toList();
		return list;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Reserva> getReservaById(@PathVariable BigDecimal id) {
		Optional<Reserva> reserva = reservaRepository.findById(id);
		if(reserva.isPresent()) {
			return ResponseEntity.ok(reserva.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Reserva createReserva(@Valid @RequestBody ReservaInput reservaInput) {
		return reservaService.saveReserva(reservaInput);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Reserva> putReserva(@PathVariable BigDecimal id, @Valid @RequestBody ReservaInput reservaInput) {
		if(!reservaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(reservaService.saveReserva(reservaInput, id, true));
	}
	
	@PostMapping("/checkin/{id}")
	public ResponseEntity<Reserva> doCheckin(@PathVariable BigDecimal id, @Valid @RequestBody CheckinInput pessoa) {
		return ResponseEntity.ok(reservaService.checkin(id, pessoa));
	}
	
	@PostMapping("/checkout/{id}")
	public ResponseEntity<Checkout> doCheckout(@PathVariable BigDecimal id) {
		return ResponseEntity.ok(reservaService.doCheckout(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Reserva> deleteReserva(@PathVariable BigDecimal id) {
		if(!reservaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		reservaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
