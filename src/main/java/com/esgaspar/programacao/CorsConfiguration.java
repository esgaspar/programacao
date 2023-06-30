package com.esgaspar.programacao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {



    /*
                    registry.addMapping("/login/*").allowedOrigins("http://localhost:4200");
                registry.addMapping("/privilegio/*").allowedOrigins("http://localhost:4200")
                        .allowedHeaders("Authorization");
                registry.addMapping("/voluntario/*").allowedOrigins("http://localhost:4200");
                registry.addMapping("/designacao/*").allowedOrigins("http://localhost:4200");
     */
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/login/*")
                .allowedOrigins("http://localhost:4200", "http://89.117.32.90:8080", "http://esgaspar.cloudns.ph")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("Authorization");

        corsRegistry.addMapping("/privilegio/*")
                .allowedOrigins("http://localhost:4200", "http://89.117.32.90:8080", "http://esgaspar.cloudns.ph")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("Authorization");

        corsRegistry.addMapping("/voluntario/*")
                .allowedOrigins("http://localhost:4200", "http://89.117.32.90:8080", "http://esgaspar.cloudns.ph")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("Authorization");

        corsRegistry.addMapping("/designacao/*")
                .allowedOrigins("http://localhost:4200", "http://89.117.32.90:8080", "http://esgaspar.cloudns.ph")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("Authorization");

    }
}