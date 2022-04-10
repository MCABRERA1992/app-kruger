package ec.kruger.corporation.java.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ec.kruger.corporation.java.entity.UsuarioRolEntity;

@Repository
public interface IUsuarioRolEntityRepository extends CrudRepository<UsuarioRolEntity, Long> {

}
