package com.esgaspar.programacao.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.esgaspar.programacao.config.SecurityProperties;
import com.esgaspar.programacao.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String ISSUER = "API";
    private static final ZoneOffset ZONE = ZoneOffset.of("-03:00");

    private final SecurityProperties props;

    public String gerarToken(User user) {
        try {
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getUsername())
                    .withExpiresAt(expiresAt())
                    .sign(Algorithm.HMAC256(props.secret()));
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token JWT", e);
        }
    }

    public String getSubject(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(props.secret()))
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    private Instant expiresAt() {
        return LocalDateTime.now().plusHours(2).toInstant(ZONE);
    }
}
