package in.gf2.seniorplaza.domain.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import in.gf2.seniorplaza.domain.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, BigDecimal> {

	Reserva findByPessoaId(BigDecimal id);
}
