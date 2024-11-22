package co.vinni.cqrs.controller;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Student;
import co.vinni.cqrs.service.PeticionQueryService;
import co.vinni.cqrs.service.StudentQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/peticion")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PeticionQueryController {
    PeticionQueryService peticionQueryService;

    @GetMapping("/")
    public List<Peticion> obtenerAll() {
        return this.peticionQueryService.getAll();
    }

}
