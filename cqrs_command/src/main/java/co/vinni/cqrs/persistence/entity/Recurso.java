package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recurso_command")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Recurso {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "recurso_seq", sequenceName = "recurso_command_seq", allocationSize = 1)
    private long code;

    private String nombre;
    private String apellido;
    private String email;
    private String mensaje;

}
