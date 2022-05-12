package in.gf2.seniorplaza.domain.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private BigDecimal id;
	
	@NotNull
	private BigDecimal quarto;
	
	@NotNull
	private BigDecimal pessoaId;
	
	@NotNull
	private Timestamp dataReserva;
	
	private Timestamp dataChegada;
	
	private Timestamp dataSaida;
	
	private boolean vagaGaragem; 
	
	private Timestamp dataCadastro;
	
	private Timestamp dataAlteracao;

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", quarto=" + quarto + ", pessoaId=" + pessoaId + ", dataReserva=" + dataReserva
				+ ", dataChegada=" + dataChegada + ", dataSaida=" + dataSaida + ", vagaGaragem=" + vagaGaragem
				+ ", dataCadastro=" + dataCadastro + ", dataAlteracao=" + dataAlteracao + "]";
	}
}