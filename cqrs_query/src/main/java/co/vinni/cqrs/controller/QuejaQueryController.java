package co.vinni.cqrs.controller;

import co.vinni.cqrs.persistence.entity.Peticion;
import co.vinni.cqrs.persistence.entity.Queja;
import co.vinni.cqrs.service.PeticionQueryService;
import co.vinni.cqrs.service.QuejaQueryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/queja")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class QuejaQueryController {
    QuejaQueryService quejaQueryService;

    @GetMapping("/")
    public List<Queja> obtenerAll() {
        return this.quejaQueryService.getAll();
    }

}
