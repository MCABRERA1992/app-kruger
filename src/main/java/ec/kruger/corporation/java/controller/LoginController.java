package ec.kruger.corporation.java.controller;

import ec.kruger.corporation.java.dto.ClaveValorDto;
import ec.kruger.corporation.java.dto.UserDto;
import ec.kruger.corporation.java.entity.service.IUsuarioEntityService;
import ec.kruger.corporation.java.exception.KrugerException;
import ec.kruger.corporation.java.security.jwt.JwtDto;
import ec.kruger.corporation.java.security.jwt.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login-security")
    public ResponseEntity<?> loginSecurity(@RequestBody UserDto userDto){

        log.info("Iniciando proceso de autenticacion {LoginController.loginSecurity}");
        if (StringUtils.isEmpty(userDto.getUsuario()) || StringUtils.isEmpty(userDto.getClave())) {
            log.warn("El usuario y la clave no deben estar vacios");
            throw new KrugerException("El usuario y la clave no deben estar vacios");
        }
        try {
            //Aparatir de una autenticacion se crea el token
            log.info("Generando autenticaicion en el proceso {LoginController.loginSecurity}");
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsuario(), userDto.getClave()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = this.jwtProvider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            JwtDto jwto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
            log.info("Proceso correctamente de {LoginController.loginSecurity} " + jwto);
            return new ResponseEntity<JwtDto>(jwto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error en el proceso de autenticacion {LoginController.loginSecurity} " + e);
            throw new KrugerException("Error en el proceso de autenticacion {LoginController.loginSecurity} " + e);
        }
    }
}
