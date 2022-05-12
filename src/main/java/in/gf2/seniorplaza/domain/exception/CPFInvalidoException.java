package in.gf2.seniorplaza.domain.exception;

public class CPFInvalidoException extends NegocioException {
	private static final long serialVersionUID = 1L;
	
	public CPFInvalidoException() {
		super("CPF inválido.");
	}
}
