package ec.kruger.corporation.java.security.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:38
 * @project app-vacunacion
 * @Version 1.0
 **/
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            log.info("Iniciando proceso de {JwtTokenFilter.doFilterInternal}");
            String token = getToken(request);
            if (Objects.nonNull(token) && this.jwtProvider.validateToken(token)) {
                String nameUser = this.jwtProvider.userNameFromToken(token);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(nameUser);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        } catch (Exception e) {
            log.error("Error en el proceso de {JwtTokenFilter.doFilterInternal} " + e);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        log.info("Iniciando proceso de {JwtTokenFilter.getToken}");
        String header = request.getHeader("Authorization");
        if (Objects.nonNull(header) && StringUtils.startsWith(header, "Bearer")) {
            log.info("Iniciando proceso de replace en {JwtTokenFilter.getToken}");
            return header.replace("Bearer ", "");
        }
        log.warn("Ocurrio un problema en el proceso de {JwtTokenFilter.getToken}");
        return null;
    }
}
