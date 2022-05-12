package in.gf2.seniorplaza.domain.exception;

public class InvalidDateException extends NegocioException {
	private static final long serialVersionUID = 1L;

	public InvalidDateException(String msg) {
		super(msg);
	}
}
