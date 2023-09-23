package com.corpotrate.lookiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.apache.catalina.connector.Connector;

@EnableJpaAuditing
@SpringBootApplication
public class LookizApplication {

	public static void main(String[] args) {
		SpringApplication.run(LookizApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
			}
		};
	}

	@Bean
	public ServletWebServerFactory serverFactory() {
		TomcatServletWebServerFactory tomcatServletWebServerFactory
				= new TomcatServletWebServerFactory();
		tomcatServletWebServerFactory.addAdditionalTomcatConnectors(createStandardConnector());

		return tomcatServletWebServerFactory;
	}

	private Connector createStandardConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setPort(8080);

		return connector;
	}

}
