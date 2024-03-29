package com.carhouse.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validator;

/**
 * The Web config.
 */
@Configuration
@EnableWebMvc
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:sql-query.properties")
})
@ComponentScan(basePackages = {"com.carhouse"})
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * Local validator factory bean validator.
     * Enable bean validation
     *
     * @return the validator
     */
    @Bean
    public Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Method validation post processor.
     * It is used to enable query and path parameter validation
     *
     * @return the method validation post processor
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    /**
     * Create MultipartResolver to upload files in html form.
     *
     * @return multipartResolver
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver(@Value("${max.upload.size}") String maxUploadSize) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(Integer.parseInt(maxUploadSize));
        return multipartResolver;
    }

    /**
     * Gets object mapper.
     * It is used to convert object to JSON and back
     *
     * @return the object mapper
     */
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
