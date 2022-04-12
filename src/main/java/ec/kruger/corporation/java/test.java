package ec.kruger.corporation.java;

import java.util.ArrayList;
import java.util.List;

import ec.kruger.corporation.java.entity.EstadoVacunaEntity;
import ec.kruger.corporation.java.entity.service.IEstadoVacunaEntityService;
import ec.kruger.corporation.java.enums.EnumUsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ec.kruger.corporation.java.entity.UsuarioEntity;
import ec.kruger.corporation.java.entity.UsuarioRolEntity;
import ec.kruger.corporation.java.entity.service.IUsuarioEntityService;
import ec.kruger.corporation.java.entity.service.IUsuarioRolEntityService;

@Component
public class test implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioEntityService entityService;
	
	@Autowired
	private IUsuarioRolEntityService iUsuarioRolEntityService;

	@Override
	public void run(String... args) throws Exception {
		
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setClave(passwordEncoder.encode("KRUGER"));
		usuarioEntity.setUserKruger("KRUGER");

		List<UsuarioRolEntity> usuarioRolEntityHashMap = new ArrayList<>();
		UsuarioRolEntity usuarioRolEntity = new UsuarioRolEntity();
		usuarioRolEntity.setEnumUsuarioRol(EnumUsuarioRol.ROL_ADMIN);
		usuarioRolEntity.setUsuarioEntity(usuarioEntity);
		usuarioRolEntityHashMap.add(usuarioRolEntity);

		usuarioEntity.setUsuarioRolEntities(usuarioRolEntityHashMap);
		this.entityService.saveOrUpdate(usuarioEntity);

	}

}
