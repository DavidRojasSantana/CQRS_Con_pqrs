package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.SugerenciaEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Sugerencia;
import co.vinni.cqrs.persistence.repository.PeticionRepository;
import co.vinni.cqrs.persistence.repository.SugerenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SugerenciaQueryService {
    SugerenciaRepository sugerenciaRepository;
    public List<Sugerencia> getAll() {
        return this.sugerenciaRepository.findAll();
    }

    @KafkaListener(topics = "sugerencia-event-topic",groupId = "sugerencia-event-group")
    public void processProductEvents(SugerenciaEvent peticionEvent) {
        Sugerencia peticionDO = peticionEvent.getSugerencia();
        if (peticionEvent.getEventType().equals("CreateSugerencia")) {
            sugerenciaRepository.save(peticionDO);
        }
        if (peticionEvent.getEventType().equals("UpdateSugerencia")) {
            Sugerencia existinPeticion = sugerenciaRepository.findById(peticionDO.getCode()).get();
            existinPeticion.setNombre(peticionDO.getNombre());
            existinPeticion.setApellido(peticionDO.getApellido());
            existinPeticion.setEmail(peticionDO.getEmail());
            existinPeticion.setMensaje(peticionDO.getMensaje());
            sugerenciaRepository.save(existinPeticion);
        }
    }
}
