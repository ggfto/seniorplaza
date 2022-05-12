package in.gf2.seniorplaza.domain.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import in.gf2.seniorplaza.Utils;
import in.gf2.seniorplaza.api.model.Checkout;
import in.gf2.seniorplaza.api.model.Invoice;
import in.gf2.seniorplaza.domain.model.Reserva;

@Service
public class CheckoutService {
	public Checkout checkout(Reserva reservation) {
		var checkout = new Checkout();
		var dtChegada = Utils.clearTime(reservation.getDataChegada());
		var dtSaida = Utils.clearTime(reservation.getDataSaida());
		var invoice = new Invoice();
		BigDecimal diariaTotal = BigDecimal.ZERO;
		while(!dtChegada.after(dtSaida)) {
			var isFDS = Utils.getDayOfWeek(dtChegada) < 2 || Utils.getDayOfWeek(dtChegada) > 6;
			var description = isFDS ? "Diária Fim de Semana" : "Diária";
			var value = isFDS ? 150 : 120;
			invoice.addInvoiceData(description, BigDecimal.valueOf(value));
			diariaTotal = diariaTotal.add(BigDecimal.valueOf(value));
			if(reservation.isVagaGaragem()) {
				description = isFDS ? "Garagem Fim de Semana": "Garagem";
				value = isFDS ? 20 : 15;
				invoice.addInvoiceData(description, BigDecimal.valueOf(value));
				diariaTotal = diariaTotal.add(BigDecimal.valueOf(value));
			}
			dtChegada = Utils.dataAdd(dtChegada, 1, 5);
		}
		dtSaida = reservation.getDataSaida();
		var dtSaidaLimite = Utils.encodeTime(dtSaida, 16, 30, 00);
		if(dtSaida.after(dtSaidaLimite)) {
			var isFDS = Utils.getDayOfWeek(dtSaida) < 2 || Utils.getDayOfWeek(dtChegada) > 6;
			var description = isFDS ? "Diária Fim de Semana" : "Diária";
			var value = isFDS ? 150 : 120;
			invoice.addInvoiceData(description, BigDecimal.valueOf(value));
			diariaTotal = diariaTotal.add(BigDecimal.valueOf(value));
			if(reservation.isVagaGaragem()) {
				description = isFDS ? "Garagem Fim de Semana": "Garagem";
				value = isFDS ? 20 : 15;
				invoice.addInvoiceData(description, BigDecimal.valueOf(value));
				diariaTotal = diariaTotal.add(BigDecimal.valueOf(value));
			}
		}
		invoice.setTotalDue(diariaTotal);
		checkout.setInvoice(invoice);
		checkout.setReservaDetail(reservation);
		return checkout;
	}
}
