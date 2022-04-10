package ec.kruger.corporation.java.entity.service.impl;

import ec.kruger.corporation.java.entity.UsuarioEntity;
import ec.kruger.corporation.java.entity.repository.IUsuarioEntityRepository;
import ec.kruger.corporation.java.entity.service.IUsuarioEntityService;
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
public class UsuarioEntityServiceImpl implements IUsuarioEntityService {

	@Autowired
	private IUsuarioEntityRepository iUsuarioEntityRepository;

	@Override
	public UsuarioEntity findByUser(String userName) {
		try {
			log.info("Iniciando proceso de {UsuarioEntityServiceImpl.findByUserName}");
			return this.iUsuarioEntityRepository.findByUserKruger(userName).get();
		} catch (Exception e) {
			log.error("Error en el proceso de {UsuarioEntityServiceImpl.findByUserName} " + e);
			throw new KrugerException("Error en el proceso de {UsuarioEntityServiceImpl.findByUserName} " + e);
		}
	}

	@Override
	public void saveOrUpdate(UsuarioEntity usuarioEntity) {
		log.info("Iniciando proceso saveOrUpdate {EstadoEntityServiceImpl.saveOrUpdate}");
		try {
			this.iUsuarioEntityRepository.save(usuarioEntity);
			log.info("Proceso finalizado saveOrUpdate {EstadoEntityServiceImpl.saveOrUpdate}");
		} catch (Exception e) {
			log.error("Error en el proceso saveOrUpdate {EstadoEntityServiceImpl.saveOrUpdate} ", e);
			throw new KrugerException("Error en el proceso saveOrUpdate {EstadoEntityServiceImpl.saveOrUpdate} " + e);
		}
	}
}
