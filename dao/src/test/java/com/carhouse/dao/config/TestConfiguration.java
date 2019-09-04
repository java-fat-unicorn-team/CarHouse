package com.carhouse.dao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

/**
 * Configuration class which provides beans to create classes.
 */
@ComponentScan("com.carhouse.dao")
@PropertySources({@PropertySource("classpath:sql-query.properties"),
        @PropertySource("classpath:query-conditions.properties")})
@Configuration
public class TestConfiguration {

}
