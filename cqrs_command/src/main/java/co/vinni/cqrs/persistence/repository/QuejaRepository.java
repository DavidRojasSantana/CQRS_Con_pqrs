package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Queja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
}
