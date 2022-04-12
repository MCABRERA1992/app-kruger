package ec.kruger.corporation.java.entity.service;

import ec.kruger.corporation.java.entity.UsuarioEntity;

/**
 * @author ${milton.cabrera} on 7/4/2022 23:08
 * @project app-vacunacion
 * @Version 1.0
 **/
public interface IUsuarioEntityService {

	void saveOrUpdate(UsuarioEntity usuarioEntity);

	UsuarioEntity findByUser(String userName);
	
	UsuarioEntity findById(Long id);
}
