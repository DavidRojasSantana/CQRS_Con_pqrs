package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.RecursoEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Recurso;
import co.vinni.cqrs.persistence.repository.PeticionRepository;
import co.vinni.cqrs.persistence.repository.RecursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecursoQueryService {
    RecursoRepository recursoRepository;
    public List<Recurso> getAll() {
        return this.recursoRepository.findAll();
    }

    @KafkaListener(topics = "recurso-event-topic",groupId = "recurso-event-group")
    public void processProductEvents(RecursoEvent peticionEvent) {
        Recurso peticionDO = peticionEvent.getRecurso();
        if (peticionEvent.getEventType().equals("CreateRecurso")) {
            recursoRepository.save(peticionDO);
        }
        if (peticionEvent.getEventType().equals("UpdateRecurso")) {
            Recurso existinPeticion = recursoRepository.findById(peticionDO.getCode()).get();
            existinPeticion.setNombre(peticionDO.getNombre());
            existinPeticion.setApellido(peticionDO.getApellido());
            existinPeticion.setEmail(peticionDO.getEmail());
            existinPeticion.setMensaje(peticionDO.getMensaje());
            recursoRepository.save(existinPeticion);
        }
    }
}
