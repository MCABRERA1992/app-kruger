package ec.kruger.corporation.java.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:37
 * @project app-vacunacion
 * @Version 1.0
 **/
@Slf4j
@Component
public class JwtEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("Error en el proceso de jwt {JwtEntryPoint.commence}");
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");
    }
}
