package com.carhouse.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The Web config.
 */
@Configuration
@EnableWebMvc
@PropertySource("classpath:sql-query.properties")
@ComponentScan({"com.carhouse"})
public class WebConfig {
}
