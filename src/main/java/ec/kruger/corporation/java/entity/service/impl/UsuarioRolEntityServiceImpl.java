package ec.kruger.corporation.java.entity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.kruger.corporation.java.entity.UsuarioRolEntity;
import ec.kruger.corporation.java.entity.repository.IUsuarioRolEntityRepository;
import ec.kruger.corporation.java.entity.service.IUsuarioRolEntityService;
import ec.kruger.corporation.java.exception.KrugerException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioRolEntityServiceImpl implements IUsuarioRolEntityService {

	@Autowired
	private IUsuarioRolEntityRepository iUsuarioRolEntityRepository;

	@Override
	public void saveOrUpdate(UsuarioRolEntity usuarioRolEntity) {
		try {
			log.info("Iniciando proceso de {UsuarioRolEntityServiceImpl.saveOrUpdate}");
			this.iUsuarioRolEntityRepository.save(usuarioRolEntity);
		} catch (Exception e) {
			log.info("Error en el proceso de {UsuarioRolEntityServiceImpl.saveOrUpdate} " + e);
			throw new KrugerException("Error en el proceso de {UsuarioRolEntityServiceImpl.saveOrUpdate} ");
		}
	}

}
