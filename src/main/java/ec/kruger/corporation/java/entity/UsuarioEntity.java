package ec.kruger.corporation.java.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:50
 * @project app-vacunacion
 * @Version 1.0
 **/
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "usuario")
    private String usuario;

    @Column(name = "clave")
    private String clave;

    @Column(name = "rol_usuario")
    private String rol_usuario;

    public UsuarioEntity() {
    }

    public UsuarioEntity(Long idUsuario, String usuario, String clave, String rol_usuario) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.clave = clave;
        this.rol_usuario = rol_usuario;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRol_usuario() {
        return rol_usuario;
    }

    public void setRol_usuario(String rol_usuario) {
        this.rol_usuario = rol_usuario;
    }
}
