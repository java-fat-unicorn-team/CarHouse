package com.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class which provides beans to create classes.
 * @author Katuranau Maksimilyan
 */
@ComponentScan("com.spring")
@PropertySource("classpath:sql-query.properties")
@Configuration
public class AppConfig {
}
