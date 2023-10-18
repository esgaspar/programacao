package com.esgaspar.programacao;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Properties;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

		Properties props = new Properties();
		// This property is used to allow the circular dependencies between the beans/classes.
		props.put("spring.main.allow-circular-references", "false");

		application.properties(props);
		return application.sources(ProgramacaoApplication.class);
	}

}
