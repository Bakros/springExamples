package com.example.demo.Chapter9_TRX1.configDB;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:testdb"); // H2 in-memory database
        config.setUsername("sa");
        config.setPassword("");
        config.setDriverClassName("org.h2.Driver");

        // HikariCP specific settings
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(600000); // 10 minutes

        DataSource hikariDS = new HikariDataSource(config);

        // Execute specific script to create the schema and populate de database
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("testcontainers/drop-schema.sql"));
        populator.addScript(new ClassPathResource("testcontainers/create-schema.sql"));
        populator.execute(hikariDS);

        return hikariDS;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }



}
