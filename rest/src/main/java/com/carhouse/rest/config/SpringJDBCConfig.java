package com.carhouse.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Spring JDBC configuration.
 * Set settings of database connections
 * Create class to work with database
 *
 * @author Katuranau Maksimilyan
 */
@Configuration
@PropertySource("classpath:${jdbc.properties:jdbc-connection}.properties")
public class SpringJDBCConfig {

    @Value("${db.driver.class.name}")
    private String DB_DRIVER_CLASS_NAME;

    @Value("${db.url}")
    private String DB_URL;

    @Value("${db.user}")
    private String DB_USER;

    @Value("${db.password}")
    private String DB_PASSWORD;

    @Value("${db.script.initialize}")
    private String DB_SCRIPT_INITIALIZE;

    @Value("${db.script.add.data}")
    private String DB_SCRIPT_ADD_DATA;

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
        dataSource.setUsername(DB_USER);
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

    /**
     * Initialize database.
     * Used only for tests
     *
     * @param dataSource is utility class to access a datasource
     * @param databasePopulator is class which sets initialization scripts for database
     * @return DataSourceInitializer
     */
    @Bean
    @Profile("integrationTest")
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource,
                                                       final ResourceDatabasePopulator databasePopulator) {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator);
        dataSourceInitializer.setEnabled(true);
        dataSourceInitializer.afterPropertiesSet();
        return dataSourceInitializer;
    }

    /**
     * Set initialization scripts for database.
     * Used only for tests
     *
     * @return ResourceDatabasePopulator
     */
    @Bean
    @Profile("integrationTest")
    public ResourceDatabasePopulator resourceDatabasePopulator() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource(DB_SCRIPT_INITIALIZE));
        databasePopulator.addScript(new ClassPathResource(DB_SCRIPT_ADD_DATA));
        return databasePopulator;
    }
}
