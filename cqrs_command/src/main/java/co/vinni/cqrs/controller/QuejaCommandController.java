package co.vinni.cqrs.controller;


import co.vinni.cqrs.dto.PeticionEvent;
import co.vinni.cqrs.dto.QuejaEvent;
import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Queja;
import co.vinni.cqrs.service.PeticionCommandService;
import co.vinni.cqrs.service.QuejaCommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queja")
//@AllArgsConstructor
@CrossOrigin(origins = "*")

public class QuejaCommandController {
    QuejaCommandService quejaCommandService;

    public QuejaCommandController(QuejaCommandService peticionCommandService) {
        this.quejaCommandService = peticionCommandService;
    }

    @PostMapping
    public ResponseEntity<Queja> createPeticion(@RequestBody QuejaEvent peticionEvent) {
        // Llamar al servicio para crear la petición
        Queja peticionGuardada = quejaCommandService.create(peticionEvent);

        // Devolver la entidad creada con el código HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(peticionGuardada);
    }

    @PostMapping("/")
    public Queja create(@RequestBody QuejaEvent quejaEvent) {
        return this.quejaCommandService.create(quejaEvent);
    }
    @PutMapping("/")
    public Queja update(@RequestBody QuejaEvent quejaEvent) {
        return this.quejaCommandService.update(quejaEvent);
    }
}
