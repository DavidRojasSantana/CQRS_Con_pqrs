package co.vinni.cqrs.controller;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Recurso;
import co.vinni.cqrs.service.PeticionQueryService;
import co.vinni.cqrs.service.RecursoQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recurso")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RecursoQueryController {
    RecursoQueryService recursoQueryService;

    @GetMapping("/")
    public List<Recurso> obtenerAll() {
        return this.recursoQueryService.getAll();
    }

}
