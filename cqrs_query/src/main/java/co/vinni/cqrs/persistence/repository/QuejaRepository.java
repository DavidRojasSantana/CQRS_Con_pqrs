package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Queja;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuejaRepository extends MongoRepository<Queja, String> {
}
