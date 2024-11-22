package co.vinni.cqrs.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sugerencia_command")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sugerencia {
    @Id
    @GeneratedValue
    @SequenceGenerator(name = "sugerencia_seq", sequenceName = "sugerencia_command_seq", allocationSize = 1)
    private long code;

    private String nombre;
    private String apellido;
    private String email;
    private String mensaje;

}
