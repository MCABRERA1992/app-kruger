package ec.kruger.corporation.java.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
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
    private Timestamp fechaVacunacion;

    @Column(name = "numero_dosis")
    private int numeroDosis;

    public TipoVacunaEntity() {
    }

    public TipoVacunaEntity(Long idTipoVacuna, String tipoVacuna, Timestamp fechaVacunacion, int numeroDosis) {
        this.idTipoVacuna = idTipoVacuna;
        this.tipoVacuna = tipoVacuna;
        this.fechaVacunacion = fechaVacunacion;
        this.numeroDosis = numeroDosis;
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

    public Timestamp getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(Timestamp fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public int getNumeroDosis() {
        return numeroDosis;
    }

    public void setNumeroDosis(int numeroDosis) {
        this.numeroDosis = numeroDosis;
    }
}
