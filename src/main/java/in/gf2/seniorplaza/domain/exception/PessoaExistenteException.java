package in.gf2.seniorplaza.domain.exception;

public class PessoaExistenteException extends NegocioException {
private static final long serialVersionUID = 1L;
	
	public PessoaExistenteException(String msg) {
		super(String.format("Já existe um cliente cadastrado com este %s.", msg));
	}
}
