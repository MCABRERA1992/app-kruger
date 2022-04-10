package ec.kruger.corporation.java.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:41
 * @project app-vacunacion
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/empleado-controller")
public class EmpleadoController {

    @GetMapping("/listar-empleado")
    public ResponseEntity<?> listarEmpleado(){
        try{
            log.info("Iniciando proceso de {EmpleadoController.listarEmpleado}");
        }catch (Exception e){
            log.error("Error de proceso de {EmpleadoController.listarEmpleado}");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/saveOrUpdate-empleado")
    public ResponseEntity<?> saveOrUpdateEmpleado(){
        try{
            log.info("Iniciando proceso de {EmpleadoController.saveOrUpdateEmpleado}");
        }catch (Exception e){
            log.error("Error de proceso de {EmpleadoController.saveOrUpdateEmpleado}");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete-empleado")
    public ResponseEntity<?> deleteEmpleado(){
        try{
            log.info("Iniciando proceso de {EmpleadoController.deleteEmpleado}");
        }catch (Exception e){
            log.error("Error de proceso de {EmpleadoController.deleteEmpleado}");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
