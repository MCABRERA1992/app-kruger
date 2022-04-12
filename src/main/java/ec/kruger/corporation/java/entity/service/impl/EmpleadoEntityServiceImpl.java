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
    public List<EmpleadoEntity> criterioBusqueda(String estadoVacuna, String tipoVacuna, String fechaInicio, String fechaFin) {

        try {
            log.info("Inicando proceso de {EmpleadoEntityServiceImpl.criterioBusqueda}");
            if ((StringUtils.isNotEmpty(estadoVacuna) && StringUtils.isNotEmpty(tipoVacuna)) && ((StringUtils.isNotEmpty(fechaInicio) && StringUtils.isNotEmpty(fechaFin)))) {
                return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.buscarxFullFiltro(estadoVacuna, tipoVacuna, fechaInicio, fechaFin);
            } else if (StringUtils.isNotEmpty(fechaInicio) && StringUtils.isNotEmpty(fechaFin)) {
                return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.buscarxFiltroFecha(fechaInicio, fechaFin);
            } else if (StringUtils.isNotEmpty(estadoVacuna) && StringUtils.isNotEmpty(tipoVacuna)) {
                return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.buscarxEstadoVacunaxTipoVacuna(estadoVacuna, tipoVacuna);
            } else if (StringUtils.isNotEmpty(estadoVacuna)) {
                return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.buscarxEstadoVacuna(estadoVacuna);
            } else if (StringUtils.isNotEmpty(tipoVacuna)) {
                return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.buscarxTipoVacuna(tipoVacuna);
            } else if (Objects.nonNull(fechaInicio) && (Objects.nonNull(fechaFin))) {

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
        } catch (Exception e) {
            log.error("Error en el proceso de {EmpleadoEntityServiceImpl.delete}" + e);
            throw new KrugerException("Error en el proceso de {EmpleadoEntityServiceImpl.delete} " + e);
        }
    }

    @Override
    public List<EmpleadoEntity> buscarxuserKruger(String userKruger) {
        try {
            log.info("Inicando proceso de {EmpleadoEntityServiceImpl.buscarxuserKruger}");
            return (List<EmpleadoEntity>) this.iEmpleadoEntityRepository.buscarxuserKruger(userKruger);
        } catch (Exception e) {
            log.error("Error en el proceso de {EmpleadoEntityServiceImpl.buscarxuserKruger}" + e);
            throw new KrugerException("Error en el proceso de {EmpleadoEntityServiceImpl.buscarxuserKruger} " + e);
        }
    }

    @Override
    public EmpleadoEntity findById(Long id) {
        try {
            log.info("Inicando proceso de {EmpleadoEntityServiceImpl.findById}");
            return (EmpleadoEntity) this.iEmpleadoEntityRepository.findById(id).get();
        } catch (Exception e) {
            log.error("Error en el proceso de {EmpleadoEntityServiceImpl.findById}" + e);
            throw new KrugerException("Error en el proceso de {EmpleadoEntityServiceImpl.findById} " + e);
        }
    }
}
