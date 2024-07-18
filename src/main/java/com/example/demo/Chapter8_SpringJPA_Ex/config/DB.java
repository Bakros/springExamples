package com.example.demo.Chapter8_SpringJPA_Ex.config;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DB {

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:testdb"); // H2 in-memory database
        config.setUsername("test");
        config.setPassword("");
        config.setDriverClassName("org.h2.Driver");

        // HikariCP specific settings
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(600000); // 10 minutes

        DataSource hikariDS = new HikariDataSource(config);

        // Execute specific script to create the schema and populate de database
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("scriptContainer/DropTables.sql"));
        populator.addScript(new ClassPathResource("scriptContainer/CreateTable.sql"));

        populator.execute(hikariDS);

        try {
            ResourceDatabasePopulator populator2 = new ResourceDatabasePopulator();
            populator2.addScript(new ClassPathResource("scriptContainer/InsertData.sql"));
            //populator2.addScript(new ClassPathResource("scriptContainer/StoredFunction.sql"));
            populator2.populate(hikariDS.getConnection());
            hikariDS.getConnection().commit();
        } catch (SQLException e) {
            System.out.println("Error Seba");
            throw new RuntimeException(e);
        }


        return hikariDS;
    }

}
