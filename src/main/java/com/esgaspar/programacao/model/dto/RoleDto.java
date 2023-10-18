package com.esgaspar.programacao.model.dto;

import com.esgaspar.programacao.model.Privilegio;
import com.esgaspar.programacao.model.Role;
import com.esgaspar.programacao.model.Voluntario;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleDto {
    private Long id;
    private String name;

    @JsonIgnore
    public Role getEntity() {
        Role entity = new Role();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}
