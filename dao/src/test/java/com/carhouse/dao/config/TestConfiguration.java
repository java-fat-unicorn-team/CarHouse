package com.carhouse.dao.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Configuration class which provides beans to create classes.
 */
@ComponentScan("com.carhouse.dao")
@PropertySource("classpath:sql-query.properties")
@Configuration
public class TestConfiguration {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Profile("!mysql-database")
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurerH2() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new ClassPathResource("test-h2-jdbc-connection.properties"));
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }

    @Profile("mysql-database")
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurerMySQL() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new ClassPathResource("test-mysql-jdbc-connection.properties"));
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }

}
