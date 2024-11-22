package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "peticion_command")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Peticion {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "peticion_seq", sequenceName = "peticion_command_seq", allocationSize = 1)
    private long code;

    private String nombre;
    private String apellido;
    private String email;
    private String mensaje;

}
