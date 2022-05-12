package in.gf2.seniorplaza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.gf2.seniorplaza.domain.model.Pessoa;
import in.gf2.seniorplaza.domain.repository.PessoaRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PessoaRepositoryTest {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Test
	public void insertPessoa() {
		Pessoa p1 = new Pessoa();
		p1.setCpf("791.754.750-73");
		p1.setNome("John Doe");
		p1.setEmail("john@doe.com");
		p1.setTelefone("9999999");
		pessoaRepository.save(p1);
		Integer countPessoa = pessoaRepository.findAll().size();
		assertEquals(1, countPessoa);
		
		Pessoa p2 = pessoaRepository.findByCpfContaining("791");
		
		assertNotNull(p2);
		assertEquals(p1, p2);
	}
}
