package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.QuejaEvent;
import co.vinni.cqrs.dto.RecursoEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Queja;
import co.vinni.cqrs.persistence.entity.Recurso;
import co.vinni.cqrs.persistence.repository.PeticionRepository;
import co.vinni.cqrs.persistence.repository.RecursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RecursoCommandService {
    RecursoRepository recursoRepository;

    KafkaTemplate<String,Object> kafkaTemplate;
    /*
    public Peticion create(Peticion peticion) {
        // Guardar la petición en la base de datos
        Peticion savedPeticion = this.peticionRepository.save(peticion);

        // Crear el evento
        PeticionEvent peticionEvent = new PeticionEvent("CreatePeticion", savedPeticion);

        // Enviar el evento a Kafka
        kafkaTemplate.send("peticion-event-topic", peticionEvent);

        return savedPeticion;
    }*/
/*
    public Peticion create(PeticionEvent peticionEvent) {
        // Convierte el evento en una entidad Peticion y guárdalo en la base de datos
        Peticion peticion = peticionEvent.getPeticion();
        Peticion savedPeticion = this.peticionRepository.save(peticion);

        // Publica el evento en Kafka
        kafkaTemplate.send("peticion-event-topic", new PeticionEvent("CreatePeticion", savedPeticion));

        return savedPeticion;
    }
/*
    public Peticion create(Peticion peticion) {
        // Guardar la petición en el repositorio
        Peticion peticionDO = this.peticionRepository.save(peticion);

        // Crear el evento con el tipo y la entidad guardada
        PeticionEvent event = new PeticionEvent("CreatePeticion", peticionDO);

        // Publicar el evento en Kafka
        kafkaTemplate.send("peticion-event-topic", event);

        return peticionDO;
    }
*/
    public Recurso create(RecursoEvent recursoEvent) {
        // Guardar la entidad Peticion en la base de datos
        Recurso peticionGuardada = this.recursoRepository.save(recursoEvent.getRecurso());

        // Crear el evento con el tipo "CreatePeticion"
        RecursoEvent event = new RecursoEvent("CreateRecurso", peticionGuardada);

        // Enviar el evento a Kafka
        kafkaTemplate.send("recurso-event-topic", event);

        // Imprimir información del evento (solo para depuración)
        System.out.println("Evento enviado: " + event);

        // Retornar la entidad guardada
        return peticionGuardada;
    }
    /*
    public Recurso create(RecursoEvent peticionEvent) {
        Recurso peticoonDO=  this.recursoRepository.save(peticionEvent.getRecurso());
        RecursoEvent event=new RecursoEvent("CreateRecurso", peticoonDO);
        kafkaTemplate.send("recurso-event-topic",event);
        return peticoonDO;
    }*/
    public Recurso update(RecursoEvent peticionEvent) {
        Recurso existPeticion =  this.recursoRepository.findById(peticionEvent.getRecurso().getCode()).get();
        Recurso newPeticion = peticionEvent.getRecurso()  ;
        existPeticion.setNombre(newPeticion.getNombre());
        existPeticion.setApellido(newPeticion.getApellido());
        existPeticion.setEmail(newPeticion.getEmail());
        existPeticion.setMensaje(newPeticion.getMensaje());
        Recurso peticionDO = this.recursoRepository.save(existPeticion);
        return peticionDO;
    }
    public Recurso updateProduct(long id, RecursoEvent peticionEvent) {
        Recurso existingPeticion = recursoRepository.findById(id).get();
        Recurso newStudent=peticionEvent.getRecurso();
        existingPeticion.setNombre(newStudent.getNombre());
        existingPeticion.setApellido(newStudent.getApellido());
        existingPeticion.setEmail(newStudent.getEmail());
        existingPeticion.setMensaje(newStudent.getMensaje());
        Recurso peticionDO = recursoRepository.save(existingPeticion);
        RecursoEvent event=new RecursoEvent("UpdateRecurso", peticionDO);
        kafkaTemplate.send("recurso-event-topic", event);
        return peticionDO;
    }
}
