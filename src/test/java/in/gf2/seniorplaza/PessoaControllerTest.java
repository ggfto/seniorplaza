//package in.gf2.seniorplaza;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import in.gf2.seniorplaza.api.controller.PessoaController;
//import in.gf2.seniorplaza.domain.model.Pessoa;
//import in.gf2.seniorplaza.domain.service.PessoaCadastroService;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(PessoaController.class)
//public class PessoaControllerTest {
//
//	@TestConfiguration
//	static class TestConfig {
//
//		@Bean
//		public ModelMapper modelMapper() {
//			return new ModelMapper();
//		}
//	}
//
//	@Autowired
//	private PessoaCadastroService pessoaService;
//	
//	@Test
//	public void givenPessoas_whenGetPessoas_thenReturnJsonArray() {
//		Pessoa john = new Pessoa();
//		john.setCpf("791.754.750-73");
//		john.setNome("John Doe");
//		john.setEmail("john@doe.com");
//		john.setTelefone("9999999");
//		pessoaService.savePessoa(john);
//
//		List<Pessoa> found = pessoaService.getAllPessoasPaged(0);
//
//		assertThat(found.size()).isEqualTo(1);
//		assertThat(found.get(0)).isEqualTo(john);
//	}
//}
