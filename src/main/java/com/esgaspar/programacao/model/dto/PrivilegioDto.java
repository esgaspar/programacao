package com.esgaspar.programacao.model.dto;

import com.esgaspar.programacao.model.Privilegio;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonClassDescription
@Data
public class PrivilegioDto {
    Long id;
    String descricao;
    Integer ordem;
    String codigo;

    @Builder.Default
    Boolean checked = true;

    List<VoluntarioDto> voluntarioList;


    @JsonIgnore
    public Privilegio getEntity() {
        Privilegio privilegio = new Privilegio();
        BeanUtils.copyProperties(this, privilegio);
        return privilegio;
    }
}
