package ec.kruger.corporation.java;

/**
 * @author ${milton.cabrera} on 10/4/2022 12:55
 * @project app-vacunacion
 * @Version 1.0
 **/
public enum EnumUsuarioRol {

	ROL_ADMIN("ADMINISTRADOR", "ADMIN"), ROL_EMPLEADO("EMPLEADO", "EMPLE");

	private String decripcionRol;
	private String codRol;

	private EnumUsuarioRol(String decripcionRol, String codRol) {
		this.decripcionRol = decripcionRol;
		this.codRol = codRol;
	}

	public String getDecripcionRol() {
		return decripcionRol;
	}

	public String getCodRol() {
		return codRol;
	}
}
