package in.gf2.seniorplaza;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class SeniorplazaApplicationTests {
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}
	@Test
	void contextLoads() {
	}

}
