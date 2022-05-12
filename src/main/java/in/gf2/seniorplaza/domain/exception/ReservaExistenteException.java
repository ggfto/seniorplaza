package in.gf2.seniorplaza.domain.exception;

public class ReservaExistenteException extends NegocioException {
private static final long serialVersionUID = 1L;
	
	public ReservaExistenteException(String msg) {
		super(String.format("Esta reserva já está feita sob o id: %s.", msg));
	}
}
