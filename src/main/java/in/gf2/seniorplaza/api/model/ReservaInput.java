package in.gf2.seniorplaza.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ReservaInput {

	@NotNull
	private BigDecimal quarto;
	
	@NotNull
	private BigDecimal pessoaId;
	
	@NotBlank
	private String dataReserva;
	
	private boolean vagaGaragem; 
}
