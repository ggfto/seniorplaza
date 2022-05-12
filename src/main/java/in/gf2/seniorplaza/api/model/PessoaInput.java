package in.gf2.seniorplaza.api.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PessoaInput {
	
	@NotBlank
	@Size(max = 15)
	private String cpf;
	
	@NotBlank
	@Size(max = 255)
	private String nome;
	
	@NotBlank
	@Size(max = 20)
	private String telefone;
	
	@NotBlank
	@Email
	@Size(max = 255)
	private String email;
}
