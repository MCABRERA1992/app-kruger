package ec.kruger.corporation.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${milton.cabrera} on 10/4/2022 16:33
 * @project app-kruger
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstadoVacunaDto {

    private int id;
    private String estadoVacuna;
}
