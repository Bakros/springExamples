package com.example.Chapter6;

import com.example.demo.Chapter6.JDBCTemplate.records.repo.SingerRepo;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.MariaDBContainer;

import org.apache.commons.dbcp2.BasicDataSource;
import com.example.demo.Chapter6.JDBCTemplate.records.repo.SingerJdbcRepo;
import javax.sql.DataSource;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql({ "classpath:testcontainers/drop-schema.sql", "classpath:testcontainers/create-schema.sql" }) // This works
@SpringJUnitConfig(classes = {StoredFunctionV1_TestContainer.TestContainersConfig.class, SingerJdbcRepo.class})
public class StoredFunctionV1_TestContainer {
    private static final Logger LOGGER = LoggerFactory.getLogger(StoredFunctionV1_TestContainer.class);

    @Autowired
    SingerRepo singerRepo;

    @Autowired
    ApplicationContext context;

    @Test
    void testFindAllQuery(){
        var singers = singerRepo.findAll().collect(Collectors.toList());
        assertEquals(3, singers.size());
        LOGGER.info("Printing the info.");
        singers.forEach(singer -> LOGGER.info(singer.toString()));
    }

    @Test
    @Sql({ "classpath:testcontainers/stored-function.sql" }) // this does not! Testcontainers simply can't support all SQL dialects to 100%.
    void testStoredFunction(){
        var firstName = singerRepo.findFirstNameById(2L).orElse(null);
        assertEquals("Ben", firstName);
    }


    @Configuration
    public static class TestContainersConfig {
        private static final Logger LOGGER = LoggerFactory.getLogger(TestContainersConfig.class);
        MariaDBContainer<?> mariaDB = new MariaDBContainer<>("mariadb:10.7.4-focal");

        @PostConstruct
        void initialize() {
            mariaDB.start();
        }
        @PreDestroy
        void tearDown(){
            mariaDB.stop();
        }
        @Bean
        DataSource dataSource() {
            try {
                var dataSource = new BasicDataSource();
                dataSource.setDriverClassName(mariaDB.getDriverClassName());
                dataSource.setUrl(mariaDB.getJdbcUrl());
                dataSource.setUsername(mariaDB.getUsername());
                dataSource.setPassword(mariaDB.getPassword());
                return dataSource;
            } catch (Exception e) {
                LOGGER.error("MariaDB TestContainers DataSource bean cannot be created!", e);
                return null;
            }
        }

        @Bean
        public JdbcTemplate jdbcTemplate(DataSource dataSource) {
            //System.out.println("datasource: "+dataSource.toString());
            return new JdbcTemplate(dataSource);
        }
    }
}
