package com.esgaspar.programacao.controller;

import com.esgaspar.programacao.infra.security.TokenService;
import com.esgaspar.programacao.model.User;
import com.esgaspar.programacao.model.dto.AuthResponse;
import com.esgaspar.programacao.model.dto.LoginRequest;
import com.esgaspar.programacao.model.dto.TokenResponse;
import com.esgaspar.programacao.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        try {
            var auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            var token = tokenService.gerarToken((User) auth.getPrincipal());
            var user = userService.findByUsername(request.username());

            return ResponseEntity.ok(new AuthResponse(user, new TokenResponse(token)));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        }
    }
}
