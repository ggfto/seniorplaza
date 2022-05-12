package in.gf2.seniorplaza.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
class InvoiceData {
	private String description;
	private BigDecimal value;
}

@Getter @Setter @NoArgsConstructor
public class Invoice {
	private List<InvoiceData> data;
	private BigDecimal totalDue;
	
	public void addInvoiceData(String description, BigDecimal value) {
		if(data == null) data = new ArrayList<InvoiceData>();
		if(value != null && BigDecimal.ZERO.compareTo(value) != 0)
			data.add(new InvoiceData(description, value));
	}
}
