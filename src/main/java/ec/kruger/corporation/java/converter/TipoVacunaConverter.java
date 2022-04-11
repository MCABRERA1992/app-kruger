package ec.kruger.corporation.java.converter;

import ec.kruger.corporation.java.dto.TipoVacunaDto;
import ec.kruger.corporation.java.entity.TipoVacunaEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Function;

/**
 * @author ${milton.cabrera} on 10/4/2022 17:04
 * @project app-kruger
 * @Version 1.0
 **/
public class TipoVacunaConverter {

    public static final Function<TipoVacunaDto, TipoVacunaEntity> VACUNA_ENTITY_FUNCTION = tipoVacunaDto -> {
        TipoVacunaEntity tipoVacunaEntity = new TipoVacunaEntity();
        tipoVacunaEntity.setTipoVacuna(tipoVacunaDto.getTipoVacunacion());
        try {
            Date fechaVacuna = new SimpleDateFormat("dd-MM-yyyy").parse(tipoVacunaDto.getFechaVacunacion());
            tipoVacunaEntity.setFechaVacunacion(fechaVacuna);
        } catch (Exception e) {

        }
        tipoVacunaEntity.setNumeroDosis(tipoVacunaDto.getNumeroDosis());
        return tipoVacunaEntity;
    };
}
