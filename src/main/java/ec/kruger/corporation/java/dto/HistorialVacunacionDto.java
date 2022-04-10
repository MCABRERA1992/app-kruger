package ec.kruger.corporation.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${milton.cabrera} on 7/4/2022 22:28
 * @project app-vacunacion
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistorialVacunacionDto {

    private String tipoVacunacion;
    private String fechaVacunacion;
    private int numeroDosis;
}
