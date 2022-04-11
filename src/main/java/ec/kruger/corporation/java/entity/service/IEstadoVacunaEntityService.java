package ec.kruger.corporation.java.entity.service;

import ec.kruger.corporation.java.entity.EstadoVacunaEntity;

/**
 * @author ${milton.cabrera} on 7/4/2022 23:08
 * @project app-vacunacion
 * @Version 1.0
 **/
public interface IEstadoVacunaEntityService {

    void saveOrUpdate(EstadoVacunaEntity estadoVacunaEntity);

    EstadoVacunaEntity findById(Long id);

}
