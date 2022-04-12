package ec.kruger.corporation.java.entity.service.impl;

import ec.kruger.corporation.java.entity.TipoVacunaEntity;
import ec.kruger.corporation.java.entity.repository.ITipoVacunaEntityRepository;
import ec.kruger.corporation.java.entity.service.ITipoVacunaEntityService;
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
public class TipoVacunaEntityServiceImpl implements ITipoVacunaEntityService {
	
    @Autowired
    private ITipoVacunaEntityRepository iTipoVacunaEntityRepository;

    @Override
    public void saveOrUpdate(TipoVacunaEntity tipoVacunaEntity) {
        log.info("Iniciando proceso saveOrUpdate {TipoVacunaEntityServiceImpl.saveOrUpdate}");
        try {
            this.iTipoVacunaEntityRepository.save(tipoVacunaEntity);
            log.info("Proceso finalizado saveOrUpdate {TipoVacunaEntityServiceImpl.saveOrUpdate}");
        } catch (Exception e) {
            log.error("Error en el proceso saveOrUpdate {TipoVacunaEntityServiceImpl.saveOrUpdate} ", e);
            throw new KrugerException("Error en el proceso saveOrUpdate {TipoVacunaEntityServiceImpl.saveOrUpdate} " + e);
        }
    }

	@Override
	public void delete(TipoVacunaEntity tipoVacunaEntity) {
		log.info("Iniciando proceso saveOrUpdate {TipoVacunaEntityServiceImpl.delete}");
        try {
            this.iTipoVacunaEntityRepository.delete(tipoVacunaEntity);
            log.info("Proceso finalizado saveOrUpdate {TipoVacunaEntityServiceImpl.delete}");
        } catch (Exception e) {
            log.error("Error en el proceso saveOrUpdate {TipoVacunaEntityServiceImpl.delete} ", e);
            throw new KrugerException("Error en el proceso saveOrUpdate {TipoVacunaEntityServiceImpl.delete} " + e);
        }
	}
}
