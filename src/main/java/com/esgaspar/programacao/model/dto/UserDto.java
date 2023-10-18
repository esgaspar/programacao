package com.esgaspar.programacao.model.dto;

import com.esgaspar.programacao.model.Privilegio;
import com.esgaspar.programacao.model.Role;
import com.esgaspar.programacao.model.User;
import com.esgaspar.programacao.model.Voluntario;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonClassDescription
@Data
public class UserDto {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<RoleDto> roles;

    @JsonIgnore
    public User getEntity() {
        User entity = new User();
        BeanUtils.copyProperties(this, entity);

        if (getRoles() != null)
            entity.setRoles(getRoles().stream().map(RoleDto::getEntity).collect(Collectors.toSet()));

        return entity;
    }
}
