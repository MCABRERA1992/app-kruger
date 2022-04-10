package ec.kruger.corporation.java.security.jwt;

import ec.kruger.corporation.java.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:50
 * @project app-vacunacion
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserPrimaryJwt implements UserDetails {

    private String nombre;
    private String user;
    private String clave;
    private String email;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static UserPrimaryJwt build(UsuarioEntity usuarioEntity) {
        List<GrantedAuthority> grantedAuthorities = usuarioEntity.getUsuarioRolEntities().stream().map(usuarioRolEntity -> new SimpleGrantedAuthority(usuarioRolEntity.getEnumUsuarioRol().name())).collect(Collectors.toList());
        return new UserPrimaryJwt("Milton Andres", usuarioEntity.getUserKruger(), usuarioEntity.getClave(), "milton.cabreralopez@gmail.com", grantedAuthorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.clave;
    }

    @Override
    public String getUsername() {
        return this.user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
