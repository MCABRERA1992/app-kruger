package ec.kruger.corporation.java.entity.repository;

import ec.kruger.corporation.java.entity.TipoVacunaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ${milton.cabrera} on 7/4/2022 23:07
 * @project app-vacunacion
 * @Version 1.0
 **/
@Repository
public interface ITipoVacunaEntityRepository extends CrudRepository<TipoVacunaEntity, Long> {
}
