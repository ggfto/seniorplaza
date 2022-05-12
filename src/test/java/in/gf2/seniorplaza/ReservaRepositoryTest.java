package in.gf2.seniorplaza;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.gf2.seniorplaza.domain.model.Reserva;
import in.gf2.seniorplaza.domain.repository.ReservaRepository;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ReservaRepositoryTest {
	
	@Autowired
	private ReservaRepository reservaRepository;

	@Test
	public void whenFindById_thenReturnReserva() {
		//given
		Reserva reserva = new Reserva();
		reserva.setPessoaId(BigDecimal.ONE);
		reserva.setDataReserva(new Timestamp(System.currentTimeMillis()));
		reserva.setQuarto(BigDecimal.ONE);
		reservaRepository.save(reserva);
		
		//when
		Optional<Reserva> r2 = reservaRepository.findById(BigDecimal.valueOf(2));
		Reserva reserva2 = r2.get();
		
		//then
		assertNotNull(reserva2);
		assertThat(reserva2).isEqualTo(reserva);
	}
	
	@Test
	public void whenFindByPessoaId_thenReturnReserva() {
		//given
		Reserva reserva = new Reserva();
		reserva.setPessoaId(BigDecimal.ONE);
		reserva.setDataReserva(new Timestamp(System.currentTimeMillis()));
		reserva.setQuarto(BigDecimal.ONE);
		reservaRepository.save(reserva);
		
		//when
		Optional<Reserva> r2 = reservaRepository.findByPessoaId(BigDecimal.ONE);
		Reserva reserva2 = r2.get();
		
		//then
		assertNotNull(reserva2);
		assertThat(reserva2).isEqualTo(reserva);
	}
}
