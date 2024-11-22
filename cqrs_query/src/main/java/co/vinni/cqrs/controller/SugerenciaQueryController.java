package co.vinni.cqrs.controller;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Sugerencia;
import co.vinni.cqrs.service.PeticionQueryService;
import co.vinni.cqrs.service.SugerenciaQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sugerencia")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SugerenciaQueryController {
    SugerenciaQueryService sugerenciaQueryService;

    @GetMapping("/")
    public List<Sugerencia> obtenerAll() {
        return this.sugerenciaQueryService.getAll();
    }

}
