package in.gf2.seniorplaza.domain.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private BigDecimal id;
	
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
	
	private Timestamp dataCadastro;
	
	private Timestamp dataAlteracao;
	
	public void setCpf(String cpf) {
		if(cpf != null) {
			cpf = cpf.replace(".", "").replace("-", "");
		}
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		return "Pessoa [codPessoa=" + id + ", cpf=" + cpf + ", nome=" + nome + ", telefone=" + telefone
				+ ", email=" + email + ", dataCadastro=" + dataCadastro + ", dataAlteracao=" + dataAlteracao + "]";
	}
}
