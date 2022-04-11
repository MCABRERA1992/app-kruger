package ec.kruger.corporation.java.entity.service.impl;

import ec.kruger.corporation.java.entity.EmpleadoEntity;
import ec.kruger.corporation.java.entity.EstadoVacunaEntity;
import ec.kruger.corporation.java.entity.TipoVacunaEntity;
import ec.kruger.corporation.java.entity.repository.IEmpleadoEntityRepository;
import ec.kruger.corporation.java.entity.service.IEmpleadoEntityService;
import ec.kruger.corporation.java.entity.service.IEstadoVacunaEntityService;
import ec.kruger.corporation.java.entity.service.ITipoVacunaEntityService;
import ec.kruger.corporation.java.exception.KrugerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author ${milton.cabrera} on 7/4/2022 23:16
 * @project app-vacunacion
 * @Version 1.0
 **/
@Slf4j
@Service
public class EmpleadoEntityServiceImpl implements IEmpleadoEntityService {

    @Autowired
    private IEmpleadoEntityRepository iEmpleadoEntityRepository;

    @Autowired
    private ITipoVacunaEntityService iTipoVacunaEntityService;

    @Override
    public void saveOrUpdate(EmpleadoEntity empleadoEntity) {
        log.info("Iniciando proceso saveOrUpdate {EmpleadoEntityServiceImpl.saveOrUpdate}");
        try {
            this.iEmpleadoEntityRepository.save(empleadoEntity);
            log.info("Proceso finalizado saveOrUpdate {EmpleadoEntityServiceImpl.saveOrUpdate}");
        } catch (Exception e) {
            log.error("Error en el proceso saveOrUpdate {EmpleadoEntityServiceImpl.saveOrUpdate} ", e);
            throw new KrugerException("Error en el proceso saveOrUpdate {EmpleadoEntityServiceImpl.saveOrUpdate} " + e);
        }
    }

    @Override
    public List<EmpleadoEntity> findAllEmpleado() {
        try {
            log.info("Inicando proceso de {EmpleadoEntityServiceImpl.findAllEmpleado}");
            return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.findAll();
        } catch (Exception e) {
            log.error("Error en el proceso de {EmpleadoEntityServiceImpl.findAllEmpleado}" + e);
            throw new KrugerException("Error en el proceso de {EmpleadoEntityServiceImpl.findAllEmpleado} " + e);
        }
    }

    @Override
    public List<EmpleadoEntity> criterioBusqueda(String estadoVacuna, String tipoVacuna, String fechaVacunacion) {
        try {
            log.info("Inicando proceso de {EmpleadoEntityServiceImpl.criterioBusqueda}");
            if (StringUtils.isNotEmpty(estadoVacuna)) {
                log.info("Inicando proceso de Busqueda {EmpleadoEntityServiceImpl.criterioBusqueda} " + estadoVacuna);
                return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.buscarxEstadoVacuna(estadoVacuna);
            } else if (StringUtils.isNotEmpty(tipoVacuna)) {
                log.info("Inicando proceso de Busqueda {EmpleadoEntityServiceImpl.criterioBusqueda} " + tipoVacuna);
                return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.buscarxTipoVacuna(tipoVacuna);
            } else if (Objects.nonNull(fechaVacunacion)) {
                log.info("Inicando proceso de Busqueda {EmpleadoEntityServiceImpl.criterioBusqueda} " + fechaVacunacion);
//                return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.findBy().;
            }
        } catch (Exception e) {
            log.error("Error en el proceso de {EmpleadoEntityServiceImpl.criterioBusqueda}" + e);
            throw new KrugerException("Error en el proceso de {EmpleadoEntityServiceImpl.criterioBusqueda} " + e);
        }
        return new ArrayList<>();
    }

    @Override
    public void delete(Long id) {
        try {
            log.info("Inicando proceso de {EmpleadoEntityServiceImpl.delete}");
            this.iEmpleadoEntityRepository.deleteById(id);
            log.info("Inicando proceso de {EmpleadoEntityServiceImpl.delete}");
        } catch (Exception e) {
            log.error("Error en el proceso de {EmpleadoEntityServiceImpl.delete}" + e);
            throw new KrugerException("Error en el proceso de {EmpleadoEntityServiceImpl.delete} " + e);
        }
    }
}
