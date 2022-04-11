package ec.kruger.corporation.java.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:51
 * @project app-vacunacion
 * @Version 1.0
 **/
@Entity
@Table(name = "tipo_vacuna")
public class TipoVacunaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_vacuna")
    private Long idTipoVacuna;

    @Column(name = "tipo_vacuna")
    private String tipoVacuna;

    @Column(name = "fecha_vacunacion")
    private Date fechaVacunacion;

    @Column(name = "numero_dosis")
    private int numeroDosis;

    @OneToOne(mappedBy = "tipoVacunaEntities")
    private EmpleadoEntity empleadoEntity;

    public TipoVacunaEntity() {
    }

    public TipoVacunaEntity(Long idTipoVacuna, String tipoVacuna, Date fechaVacunacion, int numeroDosis, EmpleadoEntity empleadoEntity) {
        this.idTipoVacuna = idTipoVacuna;
        this.tipoVacuna = tipoVacuna;
        this.fechaVacunacion = fechaVacunacion;
        this.numeroDosis = numeroDosis;
        this.empleadoEntity = empleadoEntity;
    }

    public Long getIdTipoVacuna() {
        return idTipoVacuna;
    }

    public void setIdTipoVacuna(Long idTipoVacuna) {
        this.idTipoVacuna = idTipoVacuna;
    }

    public String getTipoVacuna() {
        return tipoVacuna;
    }

    public void setTipoVacuna(String tipoVacuna) {
        this.tipoVacuna = tipoVacuna;
    }

    public Date getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(Date fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public int getNumeroDosis() {
        return numeroDosis;
    }

    public void setNumeroDosis(int numeroDosis) {
        this.numeroDosis = numeroDosis;
    }

    public EmpleadoEntity getEmpleadoEntity() {
        return empleadoEntity;
    }

    public void setEmpleadoEntity(EmpleadoEntity empleadoEntity) {
        this.empleadoEntity = empleadoEntity;
    }
}
