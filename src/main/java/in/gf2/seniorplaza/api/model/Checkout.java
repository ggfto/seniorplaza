package in.gf2.seniorplaza.api.model;

import in.gf2.seniorplaza.domain.model.Reserva;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Checkout {
	private Reserva reservaDetail;
	private Invoice invoice;
}
