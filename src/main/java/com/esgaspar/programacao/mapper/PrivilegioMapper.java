package com.esgaspar.programacao.mapper;

import com.esgaspar.programacao.model.Privilegio;
import com.esgaspar.programacao.model.Voluntario;
import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.model.dto.VoluntarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface PrivilegioMapper {

    @Mapping(target = "checked", constant = "true")
    @Mapping(target = "voluntarioList", source = "voluntarioList", qualifiedByName = "toVoluntarioParcial")
    PrivilegioDto toDto(Privilegio privilegio);

    @Mapping(target = "checked", constant = "true")
    @Mapping(target = "voluntarioList", ignore = true)
    PrivilegioDto toDtoParcial(Privilegio privilegio);

    @Mapping(target = "voluntarioList", ignore = true)
    Privilegio toEntity(PrivilegioDto dto);

    @Named("toVoluntarioParcial")
    @Mapping(target = "privilegioList", ignore = true)
    VoluntarioDto toVoluntarioParcial(Voluntario voluntario);
}
