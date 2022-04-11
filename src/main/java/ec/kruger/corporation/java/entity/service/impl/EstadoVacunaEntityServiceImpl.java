package ec.kruger.corporation.java.entity.service.impl;

import ec.kruger.corporation.java.entity.EstadoVacunaEntity;
import ec.kruger.corporation.java.entity.repository.IEstadoVacunaEntityRepository;
import ec.kruger.corporation.java.entity.service.IEstadoVacunaEntityService;
import ec.kruger.corporation.java.exception.KrugerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ${milton.cabrera} on 7/4/2022 23:17
 * @project app-vacunacion
 * @Version 1.0
 **/
@Slf4j
@Service
public class EstadoVacunaEntityServiceImpl implements IEstadoVacunaEntityService {

    @Autowired
    private IEstadoVacunaEntityRepository iEstadoVacunaEntityRepository;

    @Override
    public void saveOrUpdate(EstadoVacunaEntity estadoVacunaEntity) {
        log.info("Iniciando proceso saveOrUpdate {EstadoVacunaEntityServiceImpl.saveOrUpdate}");
        try {
            this.iEstadoVacunaEntityRepository.save(estadoVacunaEntity);
            log.info("Proceso finalizado saveOrUpdate {EstadoVacunaEntityServiceImpl.saveOrUpdate}");
        } catch (Exception e) {
            log.error("Error en el proceso saveOrUpdate {EstadoVacunaEntityServiceImpl.saveOrUpdate} ", e);
            throw new KrugerException("Error en el proceso saveOrUpdate {EstadoVacunaEntityServiceImpl.saveOrUpdate} " + e);
        }
    }

    @Override
    public EstadoVacunaEntity findById(Long id) {
        log.info("Iniciando proceso findById {EstadoVacunaEntityServiceImpl.findById}");
        try {
            return (EstadoVacunaEntity) this.iEstadoVacunaEntityRepository.findById(id).get();
        } catch (Exception e) {
            log.error("Error en el proceso saveOrUpdate {EstadoVacunaEntityServiceImpl.findById} ", e);
            throw new KrugerException("Error en el findById saveOrUpdate {EstadoVacunaEntityServiceImpl.findById} " + e);
        }
    }
}
