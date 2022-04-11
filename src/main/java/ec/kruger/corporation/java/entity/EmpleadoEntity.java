package ec.kruger.corporation.java.entity;

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
@Table(name = "empleado")
public class EmpleadoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Long idEmpleado;

    @Column(name = "cedula")
    private String cedula;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "corre_electronico")
    private String correoElectronico;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "direccion_domicilio")
    private String direccionDomicilio;

    @Column(name = "telefono_movil")
    private String telefonoMovil;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_estado_vacuna")
    private EstadoVacunaEntity estadoVacunaEntities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tipo_vacuna")
    private TipoVacunaEntity tipoVacunaEntities;

    public EmpleadoEntity() {
    }

    public EmpleadoEntity(Long idEmpleado, String cedula, String nombre, String apellido, String correoElectronico, Date fechaNacimiento, String direccionDomicilio, String telefonoMovil, EstadoVacunaEntity estadoVacunaEntities, TipoVacunaEntity tipoVacunaEntities) {
        this.idEmpleado = idEmpleado;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
        this.direccionDomicilio = direccionDomicilio;
        this.telefonoMovil = telefonoMovil;
        this.estadoVacunaEntities = estadoVacunaEntities;
        this.tipoVacunaEntities = tipoVacunaEntities;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public EstadoVacunaEntity getEstadoVacunaEntities() {
        return estadoVacunaEntities;
    }

    public void setEstadoVacunaEntities(EstadoVacunaEntity estadoVacunaEntities) {
        this.estadoVacunaEntities = estadoVacunaEntities;
    }

    public TipoVacunaEntity getTipoVacunaEntities() {
        return tipoVacunaEntities;
    }

    public void setTipoVacunaEntities(TipoVacunaEntity tipoVacunaEntities) {
        this.tipoVacunaEntities = tipoVacunaEntities;
    }
}
