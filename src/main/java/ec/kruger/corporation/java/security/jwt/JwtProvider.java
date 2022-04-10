package ec.kruger.corporation.java.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:37
 * @project app-vacunacion
 * @Version 1.0
 **/
@Slf4j
@Component
public class JwtProvider {

    public String generateToken(Authentication authentication) {
        log.info("Iniciando proceso de {JwtProvider.generateToken}");
        UserPrimaryJwt userPrimaryJwt = (UserPrimaryJwt) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrimaryJwt.getUsername())
                .setExpiration(new Date(new Date().getTime() + 5000 * 1000))
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
    }

    public String userNameFromToken(String token) {
        log.info("Iniciando proceso de {JwtProvider.userNameFromToken}");
        return Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {

        try {
            log.info("Iniciando proceso de {JwtProvider.validateToken}");
            Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Token mal formado " + e);
        } catch (UnsupportedJwtException e) {
            log.error("Token no soportado " + e);
        } catch (ExpiredJwtException e) {
            log.error("Token expirado " + e);
        } catch (IllegalArgumentException e) {
            log.error("Token vacio " + e);
        } catch (SignatureException e) {
            log.error("Error dentro de la firma " + e);
        }
        return false;
    }
}
