package ec.kruger.corporation.java.entity;

import com.sun.istack.NotNull;
import ec.kruger.corporation.java.enums.EnumUsuarioRol;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:52
 * @project app-vacunacion
 * @Version 1.0
 **/
@Entity
@Table(name = "usuario_rol_kruger")
@NamedQuery(name = "UsuarioRolEntity.findAll", query = "SELECT u FROM UsuarioRolEntity u")
public class UsuarioRolEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol_usuario")
	private long idRolUsuario;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EnumUsuarioRol enumUsuarioRol;

	@ManyToOne
	@JoinColumn(name = "fk_usuario")
	private UsuarioEntity usuarioEntity;

	public UsuarioRolEntity() {
	}

	public UsuarioRolEntity(long idRolUsuario, EnumUsuarioRol enumUsuarioRol, UsuarioEntity usuarioEntity) {
		super();
		this.idRolUsuario = idRolUsuario;
		this.enumUsuarioRol = enumUsuarioRol;
		this.usuarioEntity = usuarioEntity;
	}

	public long getIdRolUsuario() {
		return idRolUsuario;
	}

	public void setIdRolUsuario(long idRolUsuario) {
		this.idRolUsuario = idRolUsuario;
	}

	public EnumUsuarioRol getEnumUsuarioRol() {
		return enumUsuarioRol;
	}

	public void setEnumUsuarioRol(EnumUsuarioRol enumUsuarioRol) {
		this.enumUsuarioRol = enumUsuarioRol;
	}

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}

	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
