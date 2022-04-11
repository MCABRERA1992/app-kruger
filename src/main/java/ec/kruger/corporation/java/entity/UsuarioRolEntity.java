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

	public UsuarioRolEntity() {
	}

	public UsuarioRolEntity(long idRolUsuario, EnumUsuarioRol enumUsuarioRol) {
		super();
		this.idRolUsuario = idRolUsuario;
		this.enumUsuarioRol = enumUsuarioRol;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
