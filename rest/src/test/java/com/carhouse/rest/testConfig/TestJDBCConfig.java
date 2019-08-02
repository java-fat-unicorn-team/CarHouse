package com.carhouse.rest.testConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:jdbc-connection.properties")
public class TestJDBCConfig {
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
}
