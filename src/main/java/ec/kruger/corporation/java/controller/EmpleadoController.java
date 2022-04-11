package ec.kruger.corporation.java.controller;

import ec.kruger.corporation.java.converter.EmpleadoConverter;
import ec.kruger.corporation.java.converter.TipoVacunaConverter;
import ec.kruger.corporation.java.dto.EmpleadoDto;
import ec.kruger.corporation.java.entity.EmpleadoEntity;
import ec.kruger.corporation.java.entity.EstadoVacunaEntity;
import ec.kruger.corporation.java.entity.TipoVacunaEntity;
import ec.kruger.corporation.java.entity.service.IEmpleadoEntityService;
import ec.kruger.corporation.java.entity.service.IEstadoVacunaEntityService;
import ec.kruger.corporation.java.exception.KrugerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/listar-empleado")
    public ResponseEntity<?> listarEmpleado() {

        List<EmpleadoEntity> empleadoEntities = new ArrayList<>();
        try {
            log.info("Iniciando proceso de {EmpleadoController.listarEmpleado}");
            empleadoEntities = this.iEmpleadoEntityService.findAllEmpleado();
        } catch (Exception e) {
            log.error("Error de proceso de {EmpleadoController.listarEmpleado}");
            throw new KrugerException("Error de proceso de {EmpleadoController.listarEmpleado}" + e);
        }
        return new ResponseEntity<>(empleadoEntities, HttpStatus.OK);
    }

    @GetMapping("/filtros-empleado")
    public ResponseEntity<?> filtroEmpleado(@RequestParam(value = "estadoVacuna", required = false) String estadoVacuna,
                                            @RequestParam(value = "tipoVacuna",required = false) String tipoVacuna,
                                            @RequestParam(value = "fechaVacuna", required = false) String fechaVacuna) {

        List<EmpleadoEntity> empleadoEntityList = new ArrayList<>();
        try {
            log.info("Iniciando proceso de {EmpleadoController.filtroEmpleado}");
            empleadoEntityList = this.iEmpleadoEntityService.criterioBusqueda(estadoVacuna, tipoVacuna, fechaVacuna);
        } catch (Exception e) {
            log.error("Error de proceso de {EmpleadoController.filtroEmpleado}");
        }
        return new ResponseEntity<>(empleadoEntityList, HttpStatus.OK);
    }

    @PostMapping("/saveOrUpdate-empleado")
    public ResponseEntity<?> saveOrUpdateEmpleado(@RequestBody EmpleadoDto empleadoDto) {
        try {
            log.info("Iniciando proceso de {EmpleadoController.saveOrUpdateEmpleado}");
            EmpleadoEntity empleadoEntity = EmpleadoConverter.EMPLEADO_ENTITY_EMPLEADO_DTO_FUNCTION.apply(empleadoDto);
            this.iEmpleadoEntityService.saveOrUpdate(empleadoEntity);
        } catch (Exception e) {
            log.error("Error de proceso de {EmpleadoController.saveOrUpdateEmpleado}" + e);
            throw new KrugerException("Error de proceso de {EmpleadoController.saveOrUpdateEmpleado}" + e);
        }
        return new ResponseEntity<>("Transaccion Exitosa", HttpStatus.OK);
    }

    @GetMapping("/delete-empleado")
    public ResponseEntity<?> deleteEmpleado(@RequestParam("id") Long id) {
        try {
            log.info("Iniciando proceso de {EmpleadoController.deleteEmpleado}");
            this.iEmpleadoEntityService.delete(id);
        } catch (Exception e) {
            log.error("Error de proceso de {EmpleadoController.deleteEmpleado}");
        }
        return new ResponseEntity<>("Transaccion Exitosa",HttpStatus.OK);
    }
}
