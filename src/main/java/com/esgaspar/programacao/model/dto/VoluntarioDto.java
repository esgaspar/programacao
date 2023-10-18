package com.esgaspar.programacao.model.dto;

import com.esgaspar.programacao.model.Voluntario;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonClassDescription
@Data
public class VoluntarioDto {
    Long id;
    String nome;
    @Lazy List<PrivilegioDto> privilegioList;

    @JsonIgnore
    public Voluntario getEntity() {
        Voluntario entity = new Voluntario();
        BeanUtils.copyProperties(this, entity);

        if (getPrivilegioList() != null)
            entity.setPrivilegios(
                    getPrivilegioList().stream().filter(PrivilegioDto::getChecked)
                            .map(PrivilegioDto::getEntity).toList());
        return entity;
    }
}
