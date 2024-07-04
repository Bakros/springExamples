package com.example.demo.Chapter6.EmbeddedH2HikariV2.h2db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {


    @Bean
    public EmbeddedDatabase embeddedDatabase() {
        try {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)  // or HSQL, DERBY
                    .addScripts("classpath:h2/create-schema.sql",
                            "classpath:h2/test-data.sql")
                    // .addScript("classpath:schema.sql")
                    // .addScript("classpath:data.sql")  // if you have initial data to insert
                    .build();
        }catch (Exception e) {
            System.out.println("Embedded DataSource bean cannot be created!");
            return null;
        }

    }


/*
    @Bean
    public DataSource dataSource(EmbeddedDatabase embeddedDatabase) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDataSource(embeddedDatabase);
        hikariConfig.setUsername("sa");
        hikariConfig.setPassword("");
        return new HikariDataSource(hikariConfig);
    }
*/


    /*
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
     */


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
