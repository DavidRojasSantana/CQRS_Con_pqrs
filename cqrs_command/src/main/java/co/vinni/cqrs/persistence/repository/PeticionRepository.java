package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Peticion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeticionRepository extends JpaRepository<Peticion, Long> {
}
