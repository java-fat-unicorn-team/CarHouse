package com.carhouse.dao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class which provides beans to create classes.
 */
@ComponentScan("com.carhouse.dao")
@PropertySource("classpath:sql-query.properties")
@Configuration
public class TestConfiguration {

}
