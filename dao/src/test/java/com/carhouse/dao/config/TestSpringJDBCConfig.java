package com.carhouse.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Spring JDBC configuration for in-memory H2 database.
 * Set settings of database connections
 * Create class to work with database
 */
@Configuration
@PropertySource("classpath:test-jdbc-connection.properties")
public class TestSpringJDBCConfig {

    @Value("${db.script.initialize}")
    private String DB_SCRIPT_INITIALIZE;

    @Value("${db.script.add.data}")
    private String DB_SCRIPT_ADD_DATA;

    @Value("${db.driver.class.name}")
    private String DB_DRIVER_CLASS_NAME;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.user}")
    private String DB_USER_NAME;

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

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
                                                       ResourceDatabasePopulator databasePopulator) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(true);
        dataSourceInitializer.afterPropertiesSet();
        return dataSourceInitializer;
    }

    @Bean
    public ResourceDatabasePopulator resourceDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource(DB_SCRIPT_INITIALIZE));
        databasePopulator.addScript(new ClassPathResource(DB_SCRIPT_ADD_DATA));
        return databasePopulator;
    }

    /**
     * Gets named parameter jdbc template.
     *
     * @param dataSource is settings connection to database
     * @return JdbcTemplate template class with a basic set of JDBC operations
     */
    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(final DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
