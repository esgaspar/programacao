package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.infra.security.DadosTokenJWT;
import com.esgaspar.programacao.infra.security.TokenService;
import com.esgaspar.programacao.model.DadosAutenticacao;
import com.esgaspar.programacao.model.User;
import com.esgaspar.programacao.model.dto.AuthDto;
import com.esgaspar.programacao.model.dto.UserDto;
import com.esgaspar.programacao.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try {

            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

            AuthDto dto = new AuthDto();
            dto.setToken(new DadosTokenJWT(tokenJWT));

            UserDto user = userService.findByUsername(dados.username());
            dto.setUser(user);

            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}