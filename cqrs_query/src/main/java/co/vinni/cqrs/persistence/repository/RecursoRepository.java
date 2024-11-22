package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecursoRepository extends MongoRepository<Recurso, String> {
}
