package ec.kruger.corporation.java.converter;

import ec.kruger.corporation.java.dto.EmpleadoDetalleDto;
import ec.kruger.corporation.java.dto.EmpleadoDto;
import ec.kruger.corporation.java.entity.UsuarioEntity;
import ec.kruger.corporation.java.entity.UsuarioRolEntity;
import ec.kruger.corporation.java.enums.EnumUsuarioRol;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author ${milton.cabrera} on 11/4/2022 12:02
 * @project app-kruger
 * @Version 1.0
 **/
public class UsuarioConverter {


    public static final Function<EmpleadoDto, UsuarioEntity> EMPLEADO_DETALLE_DTO_USUARIO_ENTITY_FUNCTION = empleadoDetalleDto -> {

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        String[] apellidos = empleadoDetalleDto.getApellido().split(" ");
        String nombreInicial = empleadoDetalleDto.getNombre().substring(0, 1);
        usuarioEntity.setUserKruger(nombreInicial + apellidos[0]);
        
        
        List<UsuarioRolEntity> usuarioRolEntityHashMap = new ArrayList<>();
        UsuarioRolEntity usuarioRolEntity = new UsuarioRolEntity();
        usuarioRolEntity.setEnumUsuarioRol(EnumUsuarioRol.ROL_EMPLEADO);
        usuarioRolEntity.setUsuarioEntity(usuarioEntity);
        usuarioRolEntityHashMap.add(usuarioRolEntity);
        
        usuarioEntity.setUsuarioRolEntities(usuarioRolEntityHashMap);
        return usuarioEntity;
    };
}
