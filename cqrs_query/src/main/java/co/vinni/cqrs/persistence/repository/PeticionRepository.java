package co.vinni.cqrs.persistence.repository;

import co.vinni.cqrs.persistence.entity.Peticion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PeticionRepository extends MongoRepository<Peticion, String> {
}
