package com.esgaspar.programacao.mapper;

import com.esgaspar.programacao.model.Role;
import com.esgaspar.programacao.model.User;
import com.esgaspar.programacao.model.dto.RoleDto;
import com.esgaspar.programacao.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserDto toDto(User user);

    User toEntity(UserDto dto);

    RoleDto roleToDto(Role role);

    Role roleToEntity(RoleDto dto);
}
