package ec.kruger.corporation.java.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${milton.cabrera} on 7/4/2022 22:07
 * @project app-vacunacion
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpleadoDto {

    private String cedula;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String fechaNacimiento;
    private String direccionDomicilio;
    private String telefonoMovil;
    private String tipoVacunacion;
    private HistorialVacunacionDto historialVacunacionDto;

}
