package ec.kruger.corporation.java.controller;

import ec.kruger.corporation.java.dto.ClaveValorDto;
import ec.kruger.corporation.java.dto.UserDto;
import ec.kruger.corporation.java.entity.service.IUsuarioEntityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:41
 * @project app-vacunacion
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/login-controller")
public class LoginController {

    @Autowired
    private IUsuarioEntityService iUsuarioEntityService;

    @PostMapping("/login-security")
    public ResponseEntity<?> loginSecurity(@RequestBody UserDto userDto){

        ClaveValorDto claveValorDto = new ClaveValorDto();
        try{
            log.info("Iniciando proceso {LoginController.loginSecurity}");
            if(StringUtils.isEmpty(userDto.getUsuario())){
                claveValorDto.setClave(false);
                claveValorDto.setValor("Ingresar usuario, campo requerido");
            }else if(StringUtils.isEmpty(userDto.getClave())){
                claveValorDto.setClave(false);
                claveValorDto.setValor("Ingresar clave, campo requerido");
            }else {

            }

        }catch (Exception e){
            log.error("Error proceso {LoginController.loginSecurity}"+e);
        }

        return new ResponseEntity<>(claveValorDto, HttpStatus.OK);
    }
}
