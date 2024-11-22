package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SugerenciaRepository extends JpaRepository<Sugerencia, Long> {
}
