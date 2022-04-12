package ec.kruger.corporation.java.converter;

import ec.kruger.corporation.java.dto.EmpleadoDetalleDto;
import ec.kruger.corporation.java.dto.EmpleadoDto;
import ec.kruger.corporation.java.dto.EstadoVacunaDto;
import ec.kruger.corporation.java.dto.TipoVacunaDto;
import ec.kruger.corporation.java.entity.EmpleadoEntity;
import ec.kruger.corporation.java.entity.EstadoVacunaEntity;
import ec.kruger.corporation.java.entity.TipoVacunaEntity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;

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
            Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(empleadoDto.getFechaNacimiento());
            Date fechaVacunacin = new SimpleDateFormat("yyyy-MM-dd").parse(empleadoDto.getTipoVacunaDto().getFechaVacunacion());
            empleadoEntity.setFechaNacimiento(new Timestamp(fechaNacimiento.getTime()));
            tipoVacunaEntity.setFechaVacunacion(new Timestamp(fechaVacunacin.getTime()));
        } catch (Exception e) {
        }

        empleadoEntity.setTipoVacunaEntities(tipoVacunaEntity);

        return empleadoEntity;
    };


    public static final Function<List<EmpleadoEntity>, List<EmpleadoDetalleDto>> EMPLEADO_ENTITY_EMPLEADO_DETALLE_DTO_FUNCTION = empleadoEntity -> {

        List<EmpleadoDetalleDto> empleadoDetalleDtos = new ArrayList<>();
        empleadoEntity.stream().forEach(empleadoEntity1 -> {

            EmpleadoDetalleDto empleadoDetalleDto = EmpleadoDetalleDto.builder()
                    .id(empleadoEntity1.getIdEmpleado())
                    .cedula(empleadoEntity1.getCedula())
                    .nombre(empleadoEntity1.getNombre())
                    .apellidos(empleadoEntity1.getApellido())
                    .email(empleadoEntity1.getCorreoElectronico())
                    .tipoVacuna(empleadoEntity1.getTipoVacunaEntities().getTipoVacuna())
                    .numeroDosis(empleadoEntity1.getTipoVacunaEntities().getNumeroDosis()).build();
            empleadoDetalleDtos.add(empleadoDetalleDto);
        });
        return empleadoDetalleDtos;
    };

    public static final Function<List<EmpleadoEntity>, EmpleadoDto> LIST_EMPLEADO_DTO_FUNCTION = empleadoEntities -> {

        EmpleadoDto empleadoDto = new EmpleadoDto();
        for (EmpleadoEntity empleadoEntity1 : empleadoEntities) {

            TipoVacunaDto tipoVacunaDto = TipoVacunaDto.builder()
                    .tipoVacunacion(empleadoEntity1.getTipoVacunaEntities().getTipoVacuna())
                    .fechaVacunacion(new SimpleDateFormat("yyyy-MM-dd").format(empleadoEntity1.getTipoVacunaEntities().getFechaVacunacion() == null ? new Date() : empleadoEntity1.getTipoVacunaEntities().getFechaVacunacion()))
                    .numeroDosis(empleadoEntity1.getTipoVacunaEntities().getNumeroDosis()).build();

            EstadoVacunaDto estadoVacunaDto = EstadoVacunaDto.builder()
                    .id(empleadoEntity1.getEstadoVacunaEntities().getIdEstadoVacuna())
                    .estadoVacuna(empleadoEntity1.getEstadoVacunaEntities().getDescripcionEstadoVacuna()).build();

            empleadoDto = EmpleadoDto.builder()
                    .id(empleadoEntity1.getIdEmpleado())
                    .cedula(empleadoEntity1.getCedula())
                    .nombre(empleadoEntity1.getNombre())
                    .apellido(empleadoEntity1.getApellido())
                    .correoElectronico(empleadoEntity1.getCorreoElectronico())
                    .fechaNacimiento(new SimpleDateFormat("yyyy-MM-dd").format(empleadoEntity1.getFechaNacimiento() == null ? new Date() : empleadoEntity1.getFechaNacimiento()))
                    .direccionDomicilio(empleadoEntity1.getDireccionDomicilio())
                    .telefonoMovil(empleadoEntity1.getTelefonoMovil())
                    .tipoVacunaDto(tipoVacunaDto)
                    .estadoVacunaDto(estadoVacunaDto).build();

        }
        return empleadoDto;
    };


    public static final EmpleadoEntity setearValoresEmpleado(EmpleadoEntity empleadoEntity, EmpleadoDto empleadoDto) {

        empleadoEntity.setDireccionDomicilio(empleadoDto.getDireccionDomicilio());
        empleadoEntity.setTelefonoMovil(empleadoDto.getTelefonoMovil());


        empleadoEntity.getEstadoVacunaEntities().setDescripcionEstadoVacuna(empleadoDto.getEstadoVacunaDto().getEstadoVacuna());

        if (StringUtils.equalsIgnoreCase(empleadoDto.getEstadoVacunaDto().getEstadoVacuna(), "No Vacunado")) {
            empleadoEntity.getTipoVacunaEntities().setTipoVacuna("");
            empleadoEntity.getTipoVacunaEntities().setNumeroDosis(0);
            empleadoEntity.getTipoVacunaEntities().setFechaVacunacion(null);
        } else {
            empleadoEntity.getTipoVacunaEntities().setTipoVacuna(empleadoDto.getTipoVacunaDto().getTipoVacunacion());
            empleadoEntity.getTipoVacunaEntities().setNumeroDosis(empleadoDto.getTipoVacunaDto().getNumeroDosis());
        }

        try {
            Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(empleadoDto.getFechaNacimiento());
            Date fechaVacunacin = new SimpleDateFormat("yyyy-MM-dd").parse(empleadoDto.getTipoVacunaDto().getFechaVacunacion());
            empleadoEntity.setFechaNacimiento(new Timestamp(fechaNacimiento.getTime()));
            empleadoEntity.getTipoVacunaEntities().setFechaVacunacion(new Timestamp(fechaVacunacin.getTime()));
        } catch (Exception e) {
        }

        return empleadoEntity;
    }

}
