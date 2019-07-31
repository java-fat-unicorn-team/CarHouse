package database.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Spring JDBC configuration.
 * Set settings of database connections
 * Create class to work with database
 */
@Configuration
@PropertySource("classpath:h2-connection.properties")
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
     * Configuration data source (MySQL database).
     *
     * @return DataSource utility class to access a datasource
     */
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript(DB_INITIALIZE_SCRIPT)
                .addScript(DB_ADD_DATA_SCRIPT)
                .build();
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
