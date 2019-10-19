package com.carhouse.dao.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

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
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurerH2(
            @Qualifier("custom_property") Properties property) {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new ClassPathResource("test-h2-jdbc-connection.properties"));
        properties.setIgnoreResourceNotFound(false);
        properties.setProperties(property);
        return properties;
    }

    @Profile("mysql-database")
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurerMySQL(
            @Qualifier("custom_property") Properties property) {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new ClassPathResource("test-mysql-jdbc-connection.properties"));
        properties.setIgnoreResourceNotFound(false);
        properties.setProperties(property);
        return properties;
    }

    @Bean("custom_property")
    public Properties getProperties() {
        Properties property = new Properties();
        property.setProperty("image.absolute.path", getClass().getClassLoader()
                .getResource("default").getFile().replace("/default", "/"));
        return property;
    }

}
