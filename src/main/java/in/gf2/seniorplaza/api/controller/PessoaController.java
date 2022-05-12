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

import in.gf2.seniorplaza.api.model.PessoaInput;
import in.gf2.seniorplaza.domain.model.Pessoa;
import in.gf2.seniorplaza.domain.repository.PessoaRepository;
import in.gf2.seniorplaza.domain.service.PessoaCadastroService;

@RestController
@CrossOrigin
@RequestMapping("/pessoas")
public class PessoaController extends PlazaController {
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaCadastroService pessoaService;
	
	@GetMapping("/page/{page}")
	public List<Pessoa> getAllPessoaPaged(@PathVariable Integer page) {
		List<Pessoa> list = new ArrayList<>();
		Pageable paging = PageRequest.of(page, PAGE_SIZE);
		Page<Pessoa> paged = pessoaRepository.findAll(paging);
		list = paged.toList();
		return list;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPessoaById(@PathVariable BigDecimal id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		if(pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa createPessoa(@Valid @RequestBody PessoaInput pessoa) {
		return pessoaService.savePessoa(toModel(pessoa));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> updatePessoa(@PathVariable BigDecimal id, @Valid @RequestBody PessoaInput pessoaInput) {
		if(!pessoaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		Pessoa pessoa = toModel(pessoaInput);
		pessoa.setId(id);
		return ResponseEntity.ok(pessoaService.savePessoa(pessoa, true));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePessoa(@PathVariable BigDecimal id) {
		if(!pessoaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pessoaService.deletePessoa(id);
		return ResponseEntity.noContent().build();
	}
	
	private Pessoa toModel(PessoaInput item) {
		return mp.map(item, Pessoa.class);
	}
}
