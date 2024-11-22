package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "recurso")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recurso {
    @Id
    @GeneratedValue
    private String code;

    private String nombre;
    private String apellido;
    private String email;
    private String mensaje;
}
