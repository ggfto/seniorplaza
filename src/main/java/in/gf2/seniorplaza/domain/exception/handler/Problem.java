package in.gf2.seniorplaza.domain.exception.handler;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class Campo {
	private String campo;
	private String valor;
}

@Getter @Setter @NoArgsConstructor
public class Problem {
	private String message;
	private List<Campo> campos;
	
	public void addCampo(String nome, String valor) {
		if(this.campos == null) this.campos = new ArrayList<Campo>();
		this.campos.add(new Campo(nome, valor));
	}
}
