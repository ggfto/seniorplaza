package in.gf2.seniorplaza.domain.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import in.gf2.seniorplaza.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, BigDecimal> {

	Pessoa findByCpfContaining(String cpf);
	
	Pessoa findByNomeContaining(String nome);
	
	Pessoa findByEmail(String email);

	Pessoa findByCpf(String cpf);
}
