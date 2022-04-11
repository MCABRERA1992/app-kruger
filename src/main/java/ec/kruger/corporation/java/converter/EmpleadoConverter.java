package ec.kruger.corporation.java.converter;

import ec.kruger.corporation.java.dto.EmpleadoDto;
import ec.kruger.corporation.java.dto.EstadoVacunaDto;
import ec.kruger.corporation.java.entity.EmpleadoEntity;
import ec.kruger.corporation.java.entity.EstadoVacunaEntity;
import ec.kruger.corporation.java.entity.TipoVacunaEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

/**
 * @author ${milton.cabrera} on 10/4/2022 16:21
 * @project app-kruger
 * @Version 1.0
 **/
public class EmpleadoConverter {

    public static final Function<EmpleadoDto, EmpleadoEntity> EMPLEADO_ENTITY_EMPLEADO_DTO_FUNCTION = empleadoDto -> {

        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setNombre(empleadoDto.getNombre());
        empleadoEntity.setApellido(empleadoDto.getApellido());
        empleadoEntity.setCedula(empleadoDto.getCedula());
        empleadoEntity.setCorreoElectronico(empleadoDto.getCorreoElectronico());
        empleadoEntity.setDireccionDomicilio(empleadoDto.getDireccionDomicilio());
        empleadoEntity.setTelefonoMovil(empleadoDto.getTelefonoMovil());

        EstadoVacunaEntity estadoVacunaEntity = new EstadoVacunaEntity();
        estadoVacunaEntity.setDescripcionEstadoVacuna(empleadoDto.getEstadoVacunaDto().getEstadoVacuna());
        empleadoEntity.setEstadoVacunaEntities(estadoVacunaEntity);

        TipoVacunaEntity tipoVacunaEntity = new TipoVacunaEntity();
        tipoVacunaEntity.setTipoVacuna(empleadoDto.getTipoVacunaDto().getTipoVacunacion());
        tipoVacunaEntity.setNumeroDosis(empleadoDto.getTipoVacunaDto().getNumeroDosis());

        try {
            Date fechaNacimiento = new SimpleDateFormat("dd-MM-yyyy").parse(empleadoDto.getFechaNacimiento());
            Date fechaVacunacin = new SimpleDateFormat("dd-MM-yyyy").parse(empleadoDto.getTipoVacunaDto().getFechaVacunacion());
            empleadoEntity.setFechaNacimiento(fechaNacimiento);
            tipoVacunaEntity.setFechaVacunacion(fechaVacunacin);
        } catch (Exception e) {
        }

        empleadoEntity.setTipoVacunaEntities(tipoVacunaEntity);

        estadoVacunaEntity.setEmpleadoEntitys(empleadoEntity);
        tipoVacunaEntity.setEmpleadoEntity(empleadoEntity);

        return empleadoEntity;
    };
}
