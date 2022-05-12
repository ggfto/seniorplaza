package in.gf2.seniorplaza;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import in.gf2.seniorplaza.domain.model.Pessoa;

public class PessoaEntityTest {

	@Test
	public void whenSetDirtyCpf_thenReturnCleanCpf() {
		//given
		Pessoa pessoa = new Pessoa();
		
		//when
		pessoa.setCpf("111.111.111-11");
		
		//then
		assertThat(pessoa.getCpf()).isEqualTo("11111111111");
	}
}
