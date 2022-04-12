package ec.kruger.corporation.java.controller;

import ec.kruger.corporation.java.converter.EmpleadoConverter;
import ec.kruger.corporation.java.converter.TipoVacunaConverter;
import ec.kruger.corporation.java.converter.UsuarioConverter;
import ec.kruger.corporation.java.dto.ClaveValorDto;
import ec.kruger.corporation.java.dto.EmpleadoDetalleDto;
import ec.kruger.corporation.java.dto.EmpleadoDto;
import ec.kruger.corporation.java.entity.EmpleadoEntity;
import ec.kruger.corporation.java.entity.EstadoVacunaEntity;
import ec.kruger.corporation.java.entity.TipoVacunaEntity;
import ec.kruger.corporation.java.entity.UsuarioEntity;
import ec.kruger.corporation.java.entity.service.IEmpleadoEntityService;
import ec.kruger.corporation.java.entity.service.IEstadoVacunaEntityService;
import ec.kruger.corporation.java.entity.service.ITipoVacunaEntityService;
import ec.kruger.corporation.java.entity.service.IUsuarioEntityService;
import ec.kruger.corporation.java.enums.EnumUsuarioRol;
import ec.kruger.corporation.java.exception.KrugerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:41
 * @project app-vacunacion
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/empleado-controller")
public class EmpleadoController {

    @Autowired
    private IEmpleadoEntityService iEmpleadoEntityService;

    @Autowired
    private IEstadoVacunaEntityService iEstadoVacunaEntityService;

    @Autowired
    private IUsuarioEntityService iUsuarioEntityService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private ITipoVacunaEntityService iTipoVacunaEntityService;
    

    @GetMapping("/listar-empleado")
    public ResponseEntity<?> listarEmpleado() {

        List<EmpleadoEntity> empleadoEntities = new ArrayList<>();
        try {
            log.info("Iniciando proceso de {EmpleadoController.listarEmpleado}");
            empleadoEntities = this.iEmpleadoEntityService.findAllEmpleado();
            if (!empleadoEntities.isEmpty()) {
                List<EmpleadoDetalleDto> empleadoDetalleDtos = EmpleadoConverter.EMPLEADO_ENTITY_EMPLEADO_DETALLE_DTO_FUNCTION.apply(empleadoEntities);
                return new ResponseEntity<>(empleadoDetalleDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Error de proceso de {EmpleadoController.listarEmpleado}");
            throw new KrugerException("Error de proceso de {EmpleadoController.listarEmpleado}" + e);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/filtros-empleado")
    public ResponseEntity<?> filtroEmpleado(@RequestParam(value = "estadoVacuna", required = false) String estadoVacuna,
                                            @RequestParam(value = "tipoVacuna", required = false) String tipoVacuna,
                                            @RequestParam(value = "fechaInicio", required = false) String fechaInicio,
                                            @RequestParam(value = "fechaFin", required = false) String fechaFin) {

        List<EmpleadoEntity> empleadoEntityList = new ArrayList<>();
        try {
            log.info("Iniciando proceso de {EmpleadoController.filtroEmpleado}");
            empleadoEntityList = this.iEmpleadoEntityService.criterioBusqueda(estadoVacuna, tipoVacuna, fechaInicio, fechaFin);
            if (!empleadoEntityList.isEmpty()) {
                List<EmpleadoDetalleDto> empleadoDetalleDto = EmpleadoConverter.EMPLEADO_ENTITY_EMPLEADO_DETALLE_DTO_FUNCTION.apply(empleadoEntityList);
                return new ResponseEntity<>(empleadoDetalleDto, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.error("Error de proceso de {EmpleadoController.filtroEmpleado}");
            throw new KrugerException("Error de proceso de {EmpleadoController.filtroEmpleado}" + e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/saveOrUpdate-empleado")
    public ResponseEntity<?> saveOrUpdateEmpleado(@RequestBody EmpleadoDto empleadoDto) {

    	ClaveValorDto claveValorDto = new ClaveValorDto();
        try {
            log.info("Iniciando proceso de {EmpleadoController.saveOrUpdateEmpleado}");
            if(empleadoDto.getId() == 0 ) {
	            UsuarioEntity usuarioEntity = UsuarioConverter.EMPLEADO_DETALLE_DTO_USUARIO_ENTITY_FUNCTION.apply(empleadoDto);
	            usuarioEntity.setClave(passwordEncoder.encode(usuarioEntity.getUserKruger()));
	            this.iUsuarioEntityService.saveOrUpdate(usuarioEntity);
	            EmpleadoEntity empleadoEntity = EmpleadoConverter.EMPLEADO_ENTITY_EMPLEADO_DTO_FUNCTION.apply(empleadoDto);
	            empleadoEntity.setUsuarioKruger(usuarioEntity.getUserKruger());
	            this.iEmpleadoEntityService.saveOrUpdate(empleadoEntity);
	            claveValorDto = ClaveValorDto.builder().clave(true).valor("Transaccion Exitosa, Usuario Creado " + usuarioEntity.getUserKruger()).build();
	            
            }else if(empleadoDto.getId() > 0){
            	EmpleadoEntity entity = this.iEmpleadoEntityService.findById(empleadoDto.getId());            	
            	this.iEmpleadoEntityService.saveOrUpdate(EmpleadoConverter.setearValoresEmpleado(entity, empleadoDto));
            	claveValorDto = ClaveValorDto.builder().clave(true).valor("Transaccion Exitosa, Usuario Modificado " + entity.getUsuarioKruger()).build();
            }
            return new ResponseEntity<>(claveValorDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error de proceso de {EmpleadoController.saveOrUpdateEmpleado}" + e);
            throw new KrugerException("Error de proceso de {EmpleadoController.saveOrUpdateEmpleado}" + e);
        }
    }

    @GetMapping("/delete-empleado")
    public ResponseEntity<?> deleteEmpleado(@RequestParam("id") Long id) {
        try {
            log.info("Iniciando proceso de {EmpleadoController.deleteEmpleado}");
            this.iEmpleadoEntityService.delete(id);
            ClaveValorDto claveValorDto = ClaveValorDto.builder().clave(true).valor("Transaccion Exitosa").build();
            return new ResponseEntity<>(claveValorDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error de proceso de {EmpleadoController.deleteEmpleado}");
            throw new KrugerException("Error de proceso de {EmpleadoController.deleteEmpleado}" + e);
        }
    }


    @GetMapping("/filtrar-empleado-rol")
    public ResponseEntity<?> filtrarEmpleadoId(@RequestParam("user") String user,
    										   @RequestParam("rolUser") String rolUser) {
        try {
            log.info("Iniciando proceso de {EmpleadoController.filtrarEmpleadoId}");
            if (StringUtils.equalsAnyIgnoreCase(EnumUsuarioRol.ROL_EMPLEADO.getDecripcionRol(), rolUser)) {
                List<EmpleadoEntity> empleadoEntityList = this.iEmpleadoEntityService.buscarxuserKruger(user);
                if(!empleadoEntityList.isEmpty()) {
                    EmpleadoDto empleadoDto = EmpleadoConverter.LIST_EMPLEADO_DTO_FUNCTION.apply(empleadoEntityList);
                    return new ResponseEntity<>(empleadoDto, HttpStatus.OK);	
                }else {
                	return new ResponseEntity<>("No Existen Datos del Usuario "+user, HttpStatus.OK);        	
                }
            }

        } catch (Exception e) {
            log.error("Error de proceso de {EmpleadoController.filtrarEmpleadoId}");
            throw new KrugerException("Error de proceso de {EmpleadoController.filtrarEmpleadoId}" + e);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
