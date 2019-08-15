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
@PropertySource("classpath:test_jdbc-connection.properties")
public class TestJDBCConfig {

    @Value("${db.script.initialize}")
    private String DB_SCRIPT_INITIALIZE;

    @Value("${db.script.add.data}")
    private String DB_SCRIPT_ADD_DATA;

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
}
