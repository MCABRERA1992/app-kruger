package ec.kruger.corporation.java.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:36
 * @project app-vacunacion
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtDto {

    private String token;
    private String bearer = "Bearer";
    private String nameUser;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public JwtDto(String token, String nameUser, Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.token = token;
        this.nameUser = nameUser;
        this.grantedAuthorities = grantedAuthorities;
    }

}
