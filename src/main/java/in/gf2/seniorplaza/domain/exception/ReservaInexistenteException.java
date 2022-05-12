package in.gf2.seniorplaza.domain.exception;

public class ReservaInexistenteException extends NegocioException {
private static final long serialVersionUID = 1L;
	
	public ReservaInexistenteException(String msg) {
		super(String.format("A reserva sob id: %s não foi encontrada.", msg));
	}
}
