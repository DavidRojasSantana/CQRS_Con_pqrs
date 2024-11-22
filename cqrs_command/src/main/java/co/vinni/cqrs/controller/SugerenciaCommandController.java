package co.vinni.cqrs.controller;


import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.SugerenciaEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Sugerencia;
import co.vinni.cqrs.service.PeticionCommandService;
import co.vinni.cqrs.service.SugerenciaCommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sugerencia")
//@AllArgsConstructor
@CrossOrigin(origins = "*")

public class SugerenciaCommandController {
    SugerenciaCommandService sugerenciaCommandService;
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
    public SugerenciaCommandController(SugerenciaCommandService sugerenciaCommandService) {
        this.sugerenciaCommandService = sugerenciaCommandService;
    }

    @PostMapping
    public ResponseEntity<Sugerencia> createPeticion(@RequestBody SugerenciaEvent sugerenciaEvent) {
        // Llamar al servicio para crear la petición
        Sugerencia peticionGuardada = sugerenciaCommandService.create(sugerenciaEvent);

        // Devolver la entidad creada con el código HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(peticionGuardada);
    }

    @PostMapping("/")
    public Sugerencia create(@RequestBody SugerenciaEvent sugerenciaEvent) {
        return this.sugerenciaCommandService.create(sugerenciaEvent);
    }
    @PutMapping("/")
    public Sugerencia update(@RequestBody SugerenciaEvent sugerenciaEvent) {
        return this.sugerenciaCommandService.update(sugerenciaEvent);
    }
}
