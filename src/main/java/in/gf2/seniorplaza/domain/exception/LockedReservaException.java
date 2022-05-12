package in.gf2.seniorplaza.domain.exception;

public class LockedReservaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public LockedReservaException(String msg) {
		super(String.format("Não foi possível excluir o cadastro, pois existe a reserva: %s vinculada a ele.", msg));
	}
}
