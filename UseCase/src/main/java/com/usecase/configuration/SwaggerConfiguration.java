package com.usecase.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket employeeApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.usecase"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		// TODO Auto-generated method stub
		return new ApiInfoBuilder()
				.title("Employee Management - Spring Boot Swagger Configuration")
				.description("\"Swagger Configuration for My Application\"")
				.version("1.1.0")
				.license("Apache 2.0")
				.licenseUrl("https://www.apache.org/licesen.html")
				.contact(new Contact("Ruturaj Vitekar", "https://www.linkedin.com/in/ruturaj-vitekar", "ruturajvitekar0707@gmail.com"))
				.build();
	}

}
