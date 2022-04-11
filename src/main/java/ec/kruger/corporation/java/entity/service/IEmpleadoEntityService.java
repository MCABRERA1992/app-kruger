package ec.kruger.corporation.java.entity.service;

import ec.kruger.corporation.java.entity.EmpleadoEntity;
import ec.kruger.corporation.java.entity.EstadoVacunaEntity;
import ec.kruger.corporation.java.entity.TipoVacunaEntity;

import java.util.Date;
import java.util.List;

/**
 * @author ${milton.cabrera} on 7/4/2022 23:08
 * @project app-vacunacion
 * @Version 1.0
 **/
public interface IEmpleadoEntityService {

    void saveOrUpdate(EmpleadoEntity EmpleadoEntity);

    List<EmpleadoEntity> findAllEmpleado();

    List<EmpleadoEntity> criterioBusqueda(String estadoVacuna, String tipoVacuna, String fechaVacunacion);

    void delete(Long id);
}
