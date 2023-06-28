package com.esgaspar.programacao.model.dto;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonClassDescription
public class LoginDto {

    String usernameOrEmail;
    String password;
}
