package in.gf2.seniorplaza.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CheckinInput {

	@NotNull
	private BigDecimal pessoaId;
	
	private String dataChegada;
}
