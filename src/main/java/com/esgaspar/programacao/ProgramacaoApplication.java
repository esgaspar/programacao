package com.esgaspar.programacao;

import com.esgaspar.programacao.restservicecors.RestServiceCorsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;

@EnableSpringDataWebSupport
@SpringBootApplication
public class ProgramacaoApplication extends RestServiceCorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgramacaoApplication.class, args);
    }
/*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/login/*").allowedOrigins("http://localhost:4200");
                registry.addMapping("/privilegio/*").allowedOrigins("http://localhost:4200")
                        .allowedHeaders("Authorization");
                registry.addMapping("/voluntario/*").allowedOrigins("http://localhost:4200");
                registry.addMapping("/designacao/*").allowedOrigins("http://localhost:4200");
            }
        };
    }

    @Bean
// cors configuration source
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {

        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));

        corsConfiguration.addExposedHeader(HttpHeaders.AUTHORIZATION);
        corsConfiguration.addExposedHeader("userId");
        corsConfiguration.setAllowedHeaders(Arrays
                .asList("Authorization", "userId", "Cache-Control", "Content-Type"));

        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;

    }

*/
}
