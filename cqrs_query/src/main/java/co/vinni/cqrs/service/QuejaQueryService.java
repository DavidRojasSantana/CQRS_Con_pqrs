package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.QuejaEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Queja;
import co.vinni.cqrs.persistence.repository.PeticionRepository;
import co.vinni.cqrs.persistence.repository.QuejaRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuejaQueryService {
    QuejaRepository quejaRepository;
    public List<Queja> getAll() {
        return this.quejaRepository.findAll();
    }

    @KafkaListener(topics = "queja-event-topic",groupId = "queja-event-group")
    public void processProductEvents(QuejaEvent peticionEvent) {
        Queja peticionDO = peticionEvent.getQueja();
        if (peticionEvent.getEventType().equals("CreateQueja")) {
            quejaRepository.save(peticionDO);
        }
        if (peticionEvent.getEventType().equals("UpdateQueja")) {
            Queja existinPeticion = quejaRepository.findById(peticionDO.getCode()).get();
            existinPeticion.setNombre(peticionDO.getNombre());
            existinPeticion.setApellido(peticionDO.getApellido());
            existinPeticion.setEmail(peticionDO.getEmail());
            existinPeticion.setMensaje(peticionDO.getMensaje());
            quejaRepository.save(existinPeticion);
        }
    }
}
