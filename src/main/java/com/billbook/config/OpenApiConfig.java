package com.billbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI billBookOpenAPI() {

		return new OpenAPI()

				.info(new Info()

						.title("BillBook API")

						.description("BillBook Backend REST APIs for Android Application")

						.version("1.0.0")

						.contact(new Contact()

								.name("Shirin")

								.email("your-email@gmail.com")

								.url("https://github.com/ShirinCoder"))

						.license(new License()

								.name("Apache 2.0")

								.url("https://www.apache.org/licenses/LICENSE-2.0")));
	}

}
