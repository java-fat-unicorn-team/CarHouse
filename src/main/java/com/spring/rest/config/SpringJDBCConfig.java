package com.spring.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * The type Spring jdbc config.
 */
@Configuration
@PropertySource("classpath:jdbc-connection.properties")
public class SpringJDBCConfig {
    /**
     * The Driver class name.
     */
    @Value("${driver.class.name}")
    public String DRIVER_CLASS_NAME;
    /**
     * The Url.
     */
    @Value("${url}")
    public String URL;
    /**
     * The User name.
     */
    @Value("${user.name.p}")
    public String USER_NAME;
    /**
     * The Password.
     */
    @Value("${password}")
    public String PASSWORD;

    /**
     * Data source data source.
     *
     * @return DataSource data source
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER_CLASS_NAME);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    /**
     * Gets named parameter jdbc template.
     *
     * @param dataSource is settings connection to database
     * @return JdbcTemplate named parameter jdbc template
     */
    @Bean
    @Autowired
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(
            final DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
