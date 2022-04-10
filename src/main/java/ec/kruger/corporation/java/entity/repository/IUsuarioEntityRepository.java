package ec.kruger.corporation.java.entity.repository;

import ec.kruger.corporation.java.entity.UsuarioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ${milton.cabrera} on 7/4/2022 23:08
 * @project app-vacunacion
 * @Version 1.0
 **/
@Repository
public interface IUsuarioEntityRepository extends CrudRepository<UsuarioEntity, Long> {
}
