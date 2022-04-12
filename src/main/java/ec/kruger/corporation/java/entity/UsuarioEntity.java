package ec.kruger.corporation.java.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:50
 * @project app-vacunacion
 * @Version 1.0
 **/
@Entity
@Table(name = "usuario_kruger")
@NamedQuery(name = "UsuarioEntity.findAll", query = "SELECT u FROM UsuarioEntity u")
public class UsuarioEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private int idUsuario;

    @Column(name = "clave", length = 5000)
    private String clave;

    @Column(name = "user_kruger", unique = true)
    private String userKruger;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuarioEntity", cascade = CascadeType.ALL)
    private List<UsuarioRolEntity> usuarioRolEntities;

    public UsuarioEntity() {
    }

    public UsuarioEntity(int idUsuario, String clave, String userKruger, List<UsuarioRolEntity> usuarioRolEntities) {
        super();
        this.idUsuario = idUsuario;
        this.clave = clave;
        this.userKruger = userKruger;
        this.usuarioRolEntities = usuarioRolEntities;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getUserKruger() {
        return userKruger;
    }

    public void setUserKruger(String userKruger) {
        this.userKruger = userKruger;
    }

    public List<UsuarioRolEntity> getUsuarioRolEntities() {
        return usuarioRolEntities;
    }

    public void setUsuarioRolEntities(List<UsuarioRolEntity> usuarioRolEntities) {
        this.usuarioRolEntities = usuarioRolEntities;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
