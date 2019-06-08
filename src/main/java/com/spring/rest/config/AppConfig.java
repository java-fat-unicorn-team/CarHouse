package com.spring.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com.spring.rest")
@PropertySource("classpath:sql-query.properties")
@Configuration
public class AppConfig {
}
