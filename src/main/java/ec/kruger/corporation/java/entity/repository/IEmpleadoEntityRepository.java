package ec.kruger.corporation.java.entity.repository;

import ec.kruger.corporation.java.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * @author ${milton.cabrera} on 7/4/2022 21:52
 * @project app-vacunacion
 * @Version 1.0
 **/
@Repository
public interface IEmpleadoEntityRepository extends CrudRepository<EmpleadoEntity, Long> {

	@Query(value = "SELECT e FROM EmpleadoEntity e WHERE e.estadoVacunaEntities.descripcionEstadoVacuna =:estadoVacuna AND e.tipoVacunaEntities.tipoVacuna =:tipoVacuna")
    List<EmpleadoEntity> buscarxEstadoVacunaxTipoVacuna(@Param("estadoVacuna") String estadoVacuna, @Param("tipoVacuna") String tipoVacuna);

    @Query(value = "SELECT e FROM EmpleadoEntity e WHERE e.estadoVacunaEntities.descripcionEstadoVacuna =:estadoVacuna AND e.tipoVacunaEntities.tipoVacuna =:tipoVacuna " +
            "AND cast(e.tipoVacunaEntities.fechaVacunacion as date) between cast(:fechaInicio as date) AND cast(:fechaFin as date)")
    List<EmpleadoEntity> buscarxFullFiltro(@Param("estadoVacuna") String estadoVacuna, @Param("tipoVacuna") String tipoVacuna, @Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);

    @Query(value = "SELECT e FROM EmpleadoEntity e WHERE cast(e.tipoVacunaEntities.fechaVacunacion as date) between cast(:fechaInicio as date) AND cast(:fechaFin as date)")
    List<EmpleadoEntity> buscarxFiltroFecha(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);

	@Query(value = "SELECT e FROM EmpleadoEntity e WHERE e.estadoVacunaEntities.descripcionEstadoVacuna =:estadoVacuna")
    List<EmpleadoEntity> buscarxEstadoVacuna(@Param("estadoVacuna") String estadoVacuna);

    @Query(value = "SELECT e FROM EmpleadoEntity e WHERE e.tipoVacunaEntities.tipoVacuna =:tipoVacuna")
    List<EmpleadoEntity> buscarxTipoVacuna(@Param("tipoVacuna") String tipoVacuna);

    @Query(value = "SELECT e FROM EmpleadoEntity e WHERE e.usuarioKruger =:usuarioKruger")
    List<EmpleadoEntity> buscarxuserKruger(@Param("usuarioKruger") String usuarioKruger);
}
