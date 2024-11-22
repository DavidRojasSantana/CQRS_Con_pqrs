package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.StudentEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.persistence.repository.PeticionRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PeticionQueryService {
    PeticionRepository peticionRepository;
    public List<Peticion> getAll() {
        return this.peticionRepository.findAll();
    }

    @KafkaListener(topics = "peticion-event-topic",groupId = "peticion-event-group")
    public void processProductEvents(PeticionEvent peticionEvent) {
        Peticion peticionDO = peticionEvent.getPeticion();
        if (peticionEvent.getEventType().equals("CreatePeticion")) {
            peticionRepository.save(peticionDO);
        }
        if (peticionEvent.getEventType().equals("UpdatePeticion")) {
            Peticion existinPeticion = peticionRepository.findById(peticionDO.getCode()).get();
            existinPeticion.setNombre(peticionDO.getNombre());
            existinPeticion.setApellido(peticionDO.getApellido());
            existinPeticion.setEmail(peticionDO.getEmail());
            existinPeticion.setMensaje(peticionDO.getMensaje());
            peticionRepository.save(existinPeticion);
        }
    }
}
