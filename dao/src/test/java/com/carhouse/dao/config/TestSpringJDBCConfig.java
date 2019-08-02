package com.carhouse.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Spring JDBC configuration for in-memory H2 database.
 * Set settings of database connections
 * Create class to work with database
 */
@Configuration
@PropertySource("classpath:jdbc-connection.properties")
public class TestSpringJDBCConfig {
    /**
     * script which initialize database
     */
    @Value("${db.initialize.script}")
    private String DB_INITIALIZE_SCRIPT;
    /**
     * script which add data to database
     */
    @Value("${db.add.data.script}")
    private String DB_ADD_DATA_SCRIPT;
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
        databasePopulator.addScript(new ClassPathResource(DB_INITIALIZE_SCRIPT));
        databasePopulator.addScript(new ClassPathResource(DB_ADD_DATA_SCRIPT));
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
