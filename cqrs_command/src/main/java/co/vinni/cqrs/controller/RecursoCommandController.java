package co.vinni.cqrs.controller;


import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.RecursoEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Queja;
import co.vinni.cqrs.persistence.entity.Recurso;
import co.vinni.cqrs.service.PeticionCommandService;
import co.vinni.cqrs.service.RecursoCommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recurso")
//@AllArgsConstructor
@CrossOrigin(origins = "*")

public class RecursoCommandController {
    RecursoCommandService recursoCommandService;
    /*
    @PostMapping("/")
    public ResponseEntity<Peticion> create(@RequestBody Peticion peticion) {
        // Crear el evento a partir de la petición
        PeticionEvent peticionEvent = new PeticionEvent("CreatePeticion", peticion);

        // Llamar al servicio
        Peticion savedPeticion = this.peticionCommandService.create(peticionEvent);

        return ResponseEntity.ok(savedPeticion);
    }
*/
    /*
    @PostMapping("/")
    public Peticion create(@RequestBody PeticionEvent peticionEvent) {
        // Llama al método create del servicio, pasando el PeticionEvent completo
        return this.peticionCommandService.create(peticionEvent);
    }
*/
    public RecursoCommandController(RecursoCommandService recursoCommandService) {
        this.recursoCommandService = recursoCommandService;
    }

    @PostMapping
    public ResponseEntity<Recurso> createPeticion(@RequestBody RecursoEvent peticionEvent) {
        // Llamar al servicio para crear la petición
        Recurso peticionGuardada = recursoCommandService.create(peticionEvent);

        // Devolver la entidad creada con el código HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(peticionGuardada);
    }

    @PostMapping("/")
    public Recurso create(@RequestBody RecursoEvent recursoEvent) {
        return this.recursoCommandService.create(recursoEvent);
    }
    @PutMapping("/")
    public Recurso update(@RequestBody RecursoEvent recursoEvent) {
        return this.recursoCommandService.update(recursoEvent);
    }
}
