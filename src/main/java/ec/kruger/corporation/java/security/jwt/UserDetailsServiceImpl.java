package ec.kruger.corporation.java.security.jwt;

import ec.kruger.corporation.java.entity.UsuarioEntity;
import ec.kruger.corporation.java.entity.service.IUsuarioEntityService;
import ec.kruger.corporation.java.exception.KrugerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:45
 * @project app-vacunacion
 * @Version 1.0
 **/
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioEntityService iUsuarioEntityService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            log.info("Iniciando proceso de jwt {UserDetailsServiceImpl.loadUserByUsername}");
            UsuarioEntity usuarioEntity = this.iUsuarioEntityService.findByUser(userName);
            return UserPrimaryJwt.build(usuarioEntity);
        } catch (Exception e) {
            log.error("Error en el proceso de jwt {UserDetailsServiceImpl.loadUserByUsername} " + e);
            throw new KrugerException("Error en el proceso de jwt {UserDetailsServiceImpl.loadUserByUsername}");
        }
    }
}
