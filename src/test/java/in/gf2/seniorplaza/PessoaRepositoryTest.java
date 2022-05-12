package in.gf2.seniorplaza;

import static org.assertj.core.api.Assertions.assertThat;
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
	public void whenFindByCpfContaining_thenReturnPessoa() {
		//given
		Pessoa john = new Pessoa();
		john.setCpf("791.754.750-73");
		john.setNome("John Doe");
		john.setEmail("john@doe.com");
		john.setTelefone("9999999");
		pessoaRepository.save(john);
		
		//when
		Pessoa found = pessoaRepository.findByCpfContaining("791");
		
		//then
		assertNotNull(found);
		assertThat(found.getCpf()).isEqualTo(john.getCpf());
	}
	
	@Test
	public void whenFindByCpf_thenReturnPessoa() {
		//given
		Pessoa john = new Pessoa();
		john.setCpf("791.754.750-73");
		john.setNome("John Doe");
		john.setEmail("john@doe.com");
		john.setTelefone("9999999");
		pessoaRepository.save(john);
		
		//when
		Pessoa found = pessoaRepository.findByCpf("79175475073");
		
		//then
		assertNotNull(found);
		assertThat(found.getCpf()).isEqualTo(john.getCpf());
	}
	
	@Test
	public void whenFindByEmail_thenReturnPessoa() {
		//given
		Pessoa john = new Pessoa();
		john.setCpf("791.754.750-73");
		john.setNome("John Doe");
		john.setEmail("john@doe.com");
		john.setTelefone("9999999");
		pessoaRepository.save(john);
		
		//when
		Pessoa found = pessoaRepository.findByEmail("john@doe.com");
		
		//then
		assertNotNull(found);
		assertThat(found.getCpf()).isEqualTo(john.getCpf());
	}
	
	@Test
	public void whenFindByNameContaing_thenReturnPessoa() {
		//given
		Pessoa john = new Pessoa();
		john.setCpf("791.754.750-73");
		john.setNome("John Doe");
		john.setEmail("john@doe.com");
		john.setTelefone("9999999");
		pessoaRepository.save(john);
		
		//when
		Pessoa found = pessoaRepository.findByNomeContaining("Doe");
		
		//then
		assertNotNull(found);
		assertThat(found.getCpf()).isEqualTo(john.getCpf());
	}
}
