package com.carhouse.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * The Swagger configuration.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Api docket.
     * Configure swagger
     *
     * @return the docket is main bean in swagger config
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    /**
     * Add information about project on swagger-ui page.
     * @return ApiInfo object
     */
    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "CAR HOUSE",
                "This application provides endpoints for working with car sales announcements.",
                "1.0.0",
                "TERMS OF SERVICE URL",
                new Contact("NAME", "URL", "EMAIL"),
                "LICENSE",
                "LICENSE URL",
                Collections.emptyList()
        );
    }
}
