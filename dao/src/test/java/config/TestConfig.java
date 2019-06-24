package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

/**
 * Configuration class which provides beans to create classes.
 */
@ComponentScan("com.spring")
@TestPropertySource("classpath:sql-query.properties")
@Configuration
public class TestConfig {

}
