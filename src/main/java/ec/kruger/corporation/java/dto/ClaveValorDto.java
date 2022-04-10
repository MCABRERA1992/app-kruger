package ec.kruger.corporation.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${milton.cabrera} on 8/4/2022 20:53
 * @project app-vacunacion
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClaveValorDto {

    private Boolean clave;
    private Object valor;
}
