package com.example.demo.Chapter6.EmbeddedH2Hikari.h2db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

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

        var hikariDS = new HikariDataSource(config);


        return hikariDS;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
