package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.StudentEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.persistence.repository.PeticionRepository;
import co.vinni.cqrs.persistence.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PeticionCommandService {
    PeticionRepository peticionRepository;

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

    public Peticion create(PeticionEvent peticionEvent) {
        // Guardar la entidad Peticion en la base de datos
        Peticion peticionGuardada = this.peticionRepository.save(peticionEvent.getPeticion());

        // Crear el evento con el tipo "CreatePeticion"
        PeticionEvent event = new PeticionEvent("CreatePeticion", peticionGuardada);

        // Enviar el evento a Kafka
        kafkaTemplate.send("peticion-event-topic", event);

        // Imprimir información del evento (solo para depuración)
        System.out.println("Evento enviado: " + event);

        // Retornar la entidad guardada
        return peticionGuardada;
    }
    /*
    public Peticion create(PeticionEvent peticionEvent) {
        Peticion peticoonDO=  this.peticionRepository.save(peticionEvent.getPeticion());
        PeticionEvent event=new PeticionEvent("CreatePeticion", peticoonDO);
        kafkaTemplate.send("peticion-event-topic",event);
        return peticoonDO;
    }*/
    public Peticion update(PeticionEvent peticionEvent) {
        Peticion existPeticion =  this.peticionRepository.findById(peticionEvent.getPeticion().getCode()).get();
        Peticion newPeticion = peticionEvent.getPeticion()  ;
        existPeticion.setNombre(newPeticion.getNombre());
        existPeticion.setApellido(newPeticion.getApellido());
        existPeticion.setEmail(newPeticion.getEmail());
        existPeticion.setMensaje(newPeticion.getMensaje());
        Peticion peticionDO = this.peticionRepository.save(existPeticion);
        return peticionDO;
    }
    public Peticion updateProduct(long id, PeticionEvent peticionEvent) {
        Peticion existingPeticion = peticionRepository.findById(id).get();
        Peticion newStudent=peticionEvent.getPeticion();
        existingPeticion.setNombre(newStudent.getNombre());
        existingPeticion.setApellido(newStudent.getApellido());
        existingPeticion.setEmail(newStudent.getEmail());
        existingPeticion.setMensaje(newStudent.getMensaje());
        Peticion peticionDO = peticionRepository.save(existingPeticion);
        PeticionEvent event=new PeticionEvent("UpdatePeticion", peticionDO);
        kafkaTemplate.send("peticion-event-topic", event);
        return peticionDO;
    }
}
