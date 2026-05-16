package com.esgaspar.programacao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "api.security.token")
public record SecurityProperties(String secret) {}
