package com.carhouse.rest.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The Web com.carhouse.rest.com.carhouse.rest.config.
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:sql-query.properties")
@ComponentScan(basePackages = {"com.carhouse"})
public class WebConfig {
}
