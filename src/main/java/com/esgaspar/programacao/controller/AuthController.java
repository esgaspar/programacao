package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.infra.security.DadosTokenJWT;
import com.esgaspar.programacao.infra.security.TokenService;
import com.esgaspar.programacao.model.DadosAutenticacao;
import com.esgaspar.programacao.model.User;
import com.esgaspar.programacao.model.dto.AuthDto;
import com.esgaspar.programacao.model.dto.UserDto;
import com.esgaspar.programacao.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        try {
            var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
            var authentication = manager.authenticate(authenticationToken);

            var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

            AuthDto dto = new AuthDto();
            dto.setToken(new DadosTokenJWT(tokenJWT));

            UserDto user = userService.findByUsername(dados.username());
            dto.setUser(user);

            return ResponseEntity.ok(dto);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        } catch (Exception e) {
            log.error("Erro ao efetuar login", e);
            return ResponseEntity.status(500).body("Erro interno no servidor");
        }
    }
}