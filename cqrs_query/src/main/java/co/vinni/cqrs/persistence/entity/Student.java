package co.vinni.cqrs.persistence.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue
    private String code;

    private String firstName;
    private String lastName;
    private String email;

}
