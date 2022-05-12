package in.gf2.seniorplaza;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import in.gf2.seniorplaza.domain.model.Pessoa;

public class PessoaEntityTest {

	@Test
	public void PessoaTest() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("11111111111");
		assertEquals("11111111111", pessoa.getCpf());
	}
}
