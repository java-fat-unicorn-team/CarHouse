package com.carhouse.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
/**
 * Spring JDBC configuration.
 * Set settings of database connections
 * Create class to work with database
 * @author Katuranau Maksimilyan
 */
@Configuration
@PropertySource("classpath:jdbc-connection.properties")
public class SpringJDBCConfig {
    /**
     * The Driver class name.
     */
    @Value("${db.driver.class.name}")
    private String DB_DRIVER_CLASS_NAME;
    /**
     * The Url to connect database.
     */
    @Value("${db.url}")
    private String DB_URL;
    /**
     * The database's user name.
     */
    @Value("${db.user.name.p}")
    private String DB_USER_NAME;
    /**
     * The user password.
     */
    @Value("${db.password}")
    private String DB_PASSWORD;
    /**
     * Configuration data source (MySQL database).
     *
     * @return DataSource utility class to access a datasource
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER_CLASS_NAME);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USER_NAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }
    /**
     * Gets named parameter jdbc template.
     *
     * @param dataSource is settings connection to database
     * @return JdbcTemplate template class with a basic set of JDBC operations
     */
    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(
            final DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
