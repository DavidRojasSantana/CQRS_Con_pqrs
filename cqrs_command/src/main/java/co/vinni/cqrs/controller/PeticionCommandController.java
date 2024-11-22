package co.vinni.cqrs.controller;


import co.vinni.cqrs.dto.PeticionEvent;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.service.PeticionCommandService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/peticion")
//@AllArgsConstructor
@CrossOrigin(origins = "*")

public class PeticionCommandController {
    PeticionCommandService peticionCommandService;
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

    public PeticionCommandController(PeticionCommandService peticionCommandService) {
        this.peticionCommandService = peticionCommandService;
    }

    @PostMapping
    public ResponseEntity<Peticion> createPeticion(@RequestBody PeticionEvent peticionEvent) {
        // Llamar al servicio para crear la petición
        Peticion peticionGuardada = peticionCommandService.create(peticionEvent);

        // Devolver la entidad creada con el código HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(peticionGuardada);
    }



    @PostMapping("/")
    public Peticion create(@RequestBody PeticionEvent peticionEvent) {
        return this.peticionCommandService.create(peticionEvent);
    }
    @PutMapping("/")
    public Peticion update(@RequestBody PeticionEvent peticionEvent) {
        return this.peticionCommandService.update(peticionEvent);
    }
}
