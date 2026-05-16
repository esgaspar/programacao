package com.esgaspar.programacao.mapper;

import com.esgaspar.programacao.model.Privilegio;
import com.esgaspar.programacao.model.Voluntario;
import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.model.dto.VoluntarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class VoluntarioMapper {

    @Mapping(target = "privilegioList", source = "privilegios", qualifiedByName = "toPrivilegioParcial")
    public abstract VoluntarioDto toDto(Voluntario voluntario);

    @Mapping(target = "privilegioList", ignore = true)
    public abstract VoluntarioDto toDtoParcial(Voluntario voluntario);

    @Mapping(target = "privilegios", source = "privilegioList", qualifiedByName = "toCheckedPrivilegios")
    public abstract Voluntario toEntity(VoluntarioDto dto);

    @Named("toPrivilegioParcial")
    @Mapping(target = "checked", constant = "true")
    @Mapping(target = "voluntarioList", ignore = true)
    public abstract PrivilegioDto toPrivilegioParcial(Privilegio privilegio);

    @Named("toCheckedPrivilegios")
    protected List<Privilegio> toCheckedPrivilegios(List<PrivilegioDto> dtos) {
        if (dtos == null) return new ArrayList<>();
        return dtos.stream()
                .filter(p -> Boolean.TRUE.equals(p.checked()))
                .map(this::toPrivilegioEntity)
                .toList();
    }

    @Mapping(target = "voluntarioList", ignore = true)
    protected abstract Privilegio toPrivilegioEntity(PrivilegioDto dto);
}
