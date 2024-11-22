package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Sugerencia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SugerenciaRepository extends MongoRepository<Sugerencia, String> {
}
