package ec.kruger.corporation.java.entity.service;

import ec.kruger.corporation.java.entity.TipoVacunaEntity;

/**
 * @author ${milton.cabrera} on 7/4/2022 23:08
 * @project app-vacunacion
 * @Version 1.0
 **/
public interface ITipoVacunaEntityService {

    void saveOrUpdate(TipoVacunaEntity tipoVacunaEntity);
    
    void delete(TipoVacunaEntity tipoVacunaEntity);
}
