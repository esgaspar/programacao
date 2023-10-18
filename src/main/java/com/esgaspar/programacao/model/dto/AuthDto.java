package com.esgaspar.programacao.model.dto;

import com.esgaspar.programacao.infra.security.DadosTokenJWT;
import com.esgaspar.programacao.model.Designacao;
import com.esgaspar.programacao.model.User;
import com.esgaspar.programacao.util.Utils;
import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.BeanUtils;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonClassDescription
@Data
public class AuthDto {
    UserDto user;
    DadosTokenJWT token;
}
