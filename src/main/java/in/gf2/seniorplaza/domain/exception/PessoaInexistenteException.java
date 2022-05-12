package in.gf2.seniorplaza.domain.exception;

public class PessoaInexistenteException extends NegocioException {
private static final long serialVersionUID = 1L;
	
	public PessoaInexistenteException(String msg) {
		super(String.format("Pessoa sob id: %s não encontrada.", msg));
	}
}
