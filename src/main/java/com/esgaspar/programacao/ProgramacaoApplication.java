package com.esgaspar.programacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport
@SpringBootApplication
@ConfigurationPropertiesScan
public class ProgramacaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProgramacaoApplication.class, args);
    }
}
