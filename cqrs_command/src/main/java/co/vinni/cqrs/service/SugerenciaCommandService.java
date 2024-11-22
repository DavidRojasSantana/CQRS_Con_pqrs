package co.vinni.cqrs.service;

import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.StudentEvent;
import co.vinni.cqrs.dto.SugerenciaEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.persistence.entity.Sugerencia;
import co.vinni.cqrs.persistence.repository.PeticionRepository;
import co.vinni.cqrs.persistence.repository.SugerenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SugerenciaCommandService {
    SugerenciaRepository sugerenciaRepository;

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
    public Sugerencia create(SugerenciaEvent sugerenciaEvent) {
        // Guardar la entidad Peticion en la base de datos
        Sugerencia peticionGuardada = this.sugerenciaRepository.save(sugerenciaEvent.getSugerencia());

        // Crear el evento con el tipo "CreatePeticion"
        SugerenciaEvent event = new SugerenciaEvent("CreateSugerencia", peticionGuardada);

        // Enviar el evento a Kafka
        kafkaTemplate.send("sugerencia-event-topic", event);

        // Imprimir información del evento (solo para depuración)
        System.out.println("Evento enviado: " + event);

        // Retornar la entidad guardada
        return peticionGuardada;
    }


    /*
    public Sugerencia create(SugerenciaEvent   sugerenciaEvent) {
        Sugerencia studentDO=  this.sugerenciaRepository.save(sugerenciaEvent.getSugerencia());
        SugerenciaEvent event=new SugerenciaEvent("CreateSugerencia", studentDO);
        kafkaTemplate.send("sugerencia-event-topic",event);
        return studentDO;
    }*/
    public Sugerencia update(SugerenciaEvent peticionEvent) {
        Sugerencia existPeticion =  this.sugerenciaRepository.findById(peticionEvent.getSugerencia().getCode()).get();
        Sugerencia newPeticion = peticionEvent.getSugerencia()  ;
        existPeticion.setNombre(newPeticion.getNombre());
        existPeticion.setApellido(newPeticion.getApellido());
        existPeticion.setEmail(newPeticion.getEmail());
        existPeticion.setMensaje(newPeticion.getMensaje());
        Sugerencia peticionDO = this.sugerenciaRepository.save(existPeticion);
        return peticionDO;
    }
    public Sugerencia updateProduct(long id, SugerenciaEvent peticionEvent) {
        Sugerencia existingPeticion = sugerenciaRepository.findById(id).get();
        Sugerencia newStudent=peticionEvent.getSugerencia();
        existingPeticion.setNombre(newStudent.getNombre());
        existingPeticion.setApellido(newStudent.getApellido());
        existingPeticion.setEmail(newStudent.getEmail());
        existingPeticion.setMensaje(newStudent.getMensaje());
        Sugerencia peticionDO = sugerenciaRepository.save(existingPeticion);
        SugerenciaEvent event=new SugerenciaEvent("UpdateSugerencia", peticionDO);
        kafkaTemplate.send("sugerencia-event-topic", event);
        return peticionDO;
    }
}
