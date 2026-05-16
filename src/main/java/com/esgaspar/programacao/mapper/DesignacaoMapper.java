package com.esgaspar.programacao.mapper;

import com.esgaspar.programacao.model.Designacao;
import com.esgaspar.programacao.model.Privilegio;
import com.esgaspar.programacao.model.Voluntario;
import com.esgaspar.programacao.model.dto.DesignacaoDto;
import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.model.dto.VoluntarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface DesignacaoMapper {

    DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter URL_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Mapping(target = "data", source = "data", qualifiedByName = "localDateToDisplay")
    @Mapping(target = "privilegio", source = "privilegio", qualifiedByName = "privilegioParcial")
    @Mapping(target = "voluntario", source = "voluntario", qualifiedByName = "voluntarioParcial")
    DesignacaoDto toDto(Designacao designacao);

    /** Converte DTO vindo do request do frontend (data no formato dd-MM-yyyy). */
    @Mapping(target = "data", source = "data", qualifiedByName = "urlStringToDate")
    Designacao toEntityFromRequest(DesignacaoDto dto);

    /** Converte DTO vindo do banco (data no formato dd/MM/yyyy). */
    @Mapping(target = "data", source = "data", qualifiedByName = "displayStringToDate")
    Designacao toEntityFromResponse(DesignacaoDto dto);

    @Named("localDateToDisplay")
    default String localDateToDisplay(LocalDate date) {
        return date != null ? date.format(DISPLAY_FORMAT) : null;
    }

    @Named("urlStringToDate")
    default LocalDate urlStringToDate(String date) {
        return date != null ? LocalDate.parse(date, URL_FORMAT) : null;
    }

    @Named("displayStringToDate")
    default LocalDate displayStringToDate(String date) {
        return date != null ? LocalDate.parse(date, DISPLAY_FORMAT) : null;
    }

    @Named("privilegioParcial")
    @Mapping(target = "checked", constant = "true")
    @Mapping(target = "voluntarioList", ignore = true)
    PrivilegioDto toPrivilegioParcial(Privilegio privilegio);

    @Named("voluntarioParcial")
    @Mapping(target = "privilegioList", ignore = true)
    VoluntarioDto toVoluntarioParcial(Voluntario voluntario);

    @Mapping(target = "voluntarioList", ignore = true)
    Privilegio toPrivilegioEntity(PrivilegioDto dto);

    @Mapping(target = "privilegios", ignore = true)
    Voluntario toVoluntarioEntity(VoluntarioDto dto);
}
