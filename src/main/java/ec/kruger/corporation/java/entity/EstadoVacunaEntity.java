package ec.kruger.corporation.java.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:52
 * @project app-vacunacion
 * @Version 1.0
 **/
@Entity
@Table(name = "estado_vacuna")
public class EstadoVacunaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_vacuna")
    private Long idEstadoVacuna;

    @Column(name = "decripcion_estado_vacuna")
    private String descripcionEstadoVacuna;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private EmpleadoEntity empleadoEntitys;

    public EstadoVacunaEntity() {
    }

    public EstadoVacunaEntity(Long idEstadoVacuna, String descripcionEstadoVacuna, EmpleadoEntity empleadoEntitys) {
        this.idEstadoVacuna = idEstadoVacuna;
        this.descripcionEstadoVacuna = descripcionEstadoVacuna;
        this.empleadoEntitys = empleadoEntitys;
    }

    public Long getIdEstadoVacuna() {
        return idEstadoVacuna;
    }

    public void setIdEstadoVacuna(Long idEstadoVacuna) {
        this.idEstadoVacuna = idEstadoVacuna;
    }

    public String getDescripcionEstadoVacuna() {
        return descripcionEstadoVacuna;
    }

    public void setDescripcionEstadoVacuna(String descripcionEstadoVacuna) {
        this.descripcionEstadoVacuna = descripcionEstadoVacuna;
    }

    public EmpleadoEntity getEmpleadoEntitys() {
        return empleadoEntitys;
    }

    public void setEmpleadoEntitys(EmpleadoEntity empleadoEntitys) {
        this.empleadoEntitys = empleadoEntitys;
    }
}
