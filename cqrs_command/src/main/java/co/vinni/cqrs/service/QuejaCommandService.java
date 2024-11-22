package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.QuejaEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Queja;
import co.vinni.cqrs.persistence.repository.PeticionRepository;
import co.vinni.cqrs.persistence.repository.QuejaRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuejaCommandService {
    QuejaRepository quejaRepository;

    KafkaTemplate<String,Object> kafkaTemplate;

    public Queja create(QuejaEvent quejaEvent) {
        // Guardar la entidad Peticion en la base de datos
        Queja peticionGuardada = this.quejaRepository.save(quejaEvent.getQueja());


        QuejaEvent event = new QuejaEvent("CreateQueja", peticionGuardada);

        // Enviar el evento a Kafka
        kafkaTemplate.send("queja-event-topic", event);

        // Imprimir información del evento (solo para depuración)
        System.out.println("Evento enviado: " + event);

        // Retornar la entidad guardada
        return peticionGuardada;
    }
/*
    public Queja create(QuejaEvent quejaEvent) {
        Queja quejaDO=  this.quejaRepository.save(quejaEvent.getQueja());
        QuejaEvent event=new QuejaEvent("CreateQueja", quejaDO);
        kafkaTemplate.send("queja-event-topic",event);
        return quejaDO;
    }*/
    public Queja update(QuejaEvent peticionEvent) {
        Queja existPeticion =  this.quejaRepository.findById(peticionEvent.getQueja().getCode()).get();
        Queja newPeticion = peticionEvent.getQueja()  ;
        existPeticion.setNombre(newPeticion.getNombre());
        existPeticion.setApellido(newPeticion.getApellido());
        existPeticion.setEmail(newPeticion.getEmail());
        existPeticion.setMensaje(newPeticion.getMensaje());
        Queja peticionDO = this.quejaRepository.save(existPeticion);
        return peticionDO;
    }
    public Queja updateProduct(long id, QuejaEvent peticionEvent) {
        Queja existingPeticion = quejaRepository.findById(id).get();
        Queja newStudent=peticionEvent.getQueja();
        existingPeticion.setNombre(newStudent.getNombre());
        existingPeticion.setApellido(newStudent.getApellido());
        existingPeticion.setEmail(newStudent.getEmail());
        existingPeticion.setMensaje(newStudent.getMensaje());
        Queja peticionDO = quejaRepository.save(existingPeticion);
        QuejaEvent event=new QuejaEvent("UpdateQueja", peticionDO);
        kafkaTemplate.send("queja-event-topic", event);
        return peticionDO;
    }
}
