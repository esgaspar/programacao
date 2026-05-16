package com.esgaspar.programacao.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(Long id, String name, String username, String email, String password, Set<RoleDto> roles) {}
