package com.esgaspar.programacao.model.dto;

import com.esgaspar.programacao.model.Designacao;
import com.esgaspar.programacao.model.Voluntario;
import com.esgaspar.programacao.util.Utils;
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
public class DesignacaoDto {
    Long id;
    String data;
    PrivilegioDto privilegio;
    VoluntarioDto voluntario;

    @JsonIgnore
    public Designacao getEntity() {
        Designacao entity = new Designacao();
        BeanUtils.copyProperties(this, entity);
        entity.setData(Utils.StringToDateUrl(getData()));
        entity.setPrivilegio(this.getPrivilegio().getEntity());
        entity.setVoluntario(this.getVoluntario().getEntity());
        return entity;
    }

    @JsonIgnore
    public Designacao getEntityPrint() {
        Designacao entity = new Designacao();
        BeanUtils.copyProperties(this, entity);
        entity.setData(Utils.StringToDate(getData()));

        if (this.getPrivilegio() != null)
            entity.setPrivilegio(this.getPrivilegio().getEntity());
        if (this.getVoluntario() != null)
            entity.setVoluntario(this.getVoluntario().getEntity());
        return entity;
    }
}
