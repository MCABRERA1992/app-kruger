package ec.kruger.corporation.java.entity.repository;

import ec.kruger.corporation.java.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:52
 * @project app-vacunacion
 * @Version 1.0
 **/
@Repository
public interface IEmpleadoEntityRepository extends CrudRepository<EmpleadoEntity, Long> {

    @Query(value = "SELECT e FROM EmpleadoEntity e WHERE e.estadoVacunaEntities.descripcionEstadoVacuna =:estadoVacuna")
    List<EmpleadoEntity> buscarxEstadoVacuna(@Param("estadoVacuna") String estadoVacuna);

    @Query(value = "SELECT e FROM EmpleadoEntity e WHERE e.tipoVacunaEntities.tipoVacuna =:tipoVacuna")
    List<EmpleadoEntity> buscarxTipoVacuna(@Param("tipoVacuna") String tipoVacuna);
}
