package com.hackaton.cloud.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	
	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization" , "header");
	}

	private SecurityContext securityContext(){
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth(){

		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;

		return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		.apiInfo(apiInfo())
		.select()
		.apis(RequestHandlerSelectors.any())
		.paths(PathSelectors.regex("/api.*"))
		.build();
		
	}
    
	private ApiInfo apiInfo() {
		return new ApiInfo("Aplicação Cloud Learn", "API de Geranciamento Escolar Acessivel", "1.0", "Termos de Serviços",
				new Contact("Equipe Cloud", "https://github.com/Alethos-BK/Hackathon---Cloud",
						"odahcamperiles@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licesen.html", new ArrayList<>());
	}

}
