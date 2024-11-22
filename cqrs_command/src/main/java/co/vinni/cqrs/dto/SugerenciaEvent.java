package co.vinni.cqrs.dto;



import co.vinni.cqrs.persistence.entity.Sugerencia;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SugerenciaEvent {
    private String eventType;
    private Sugerencia sugerencia;
}
