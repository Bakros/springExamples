package com.example.Chapter6;

import com.example.demo.Chapter6.JDBCTemplate.records.Singer;
import com.example.demo.Chapter6.JDBCTemplate.records.repo.SingerJdbcRepo;
import com.example.demo.Chapter6.JDBCTemplate.records.repo.SingerRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import javax.sql.DataSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@Sql({ "classpath:h2/drop-schema.sql", "classpath:h2/create-schema.sql" })
@SpringJUnitConfig(classes = {JdbcRepoTestMini.EmptyEmbeddedJdbcConfig.class, SingerJdbcRepo.class})
public class JdbcRepoTestMini {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcRepoTestMini.class);

    @Autowired
    SingerRepo singerRepo;

    @Autowired
    ApplicationContext context;

    @Test
    @DisplayName("should return all singers")
    @Sql(value = "classpath:h2/test-data.sql")
    void testFindAllWithMappingSqlQuery(){

        /*
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println("Beans in ApplicationContext:");
        for (String beanName : beanNames) {
            System.out.println(beanName + " : " + context.getBean(beanName).getClass().getName());
        }
        */

        // assertAll("hello", () ->  assertNotNull(context));

        List<Singer> singers = singerRepo.findAll().collect(Collectors.toList());
        assertEquals(4, singers.size());
        LOGGER.info("Printing the info.");
        singers.forEach(singer -> LOGGER.info(singer.toString()));

    }

    @Configuration
    public static class EmptyEmbeddedJdbcConfig {
        private static final Logger LOGGER = LoggerFactory.getLogger(EmptyEmbeddedJdbcConfig.class);
        @Bean
        public DataSource dataSource() {
            try {
                var dbBuilder = new EmbeddedDatabaseBuilder();
                dbBuilder.setType(EmbeddedDatabaseType.H2);
                dbBuilder.setName("musicdb");

                return dbBuilder.build();
                //return dbBuilder.setType(EmbeddedDatabaseType.H2).setName("musicdb").build();
            } catch (Exception e) {
                LOGGER.error("Embedded DataSource bean cannot be created!", e);
                return null;
            }
        }
        @Bean
        public JdbcTemplate jdbcTemplate(DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }

    }
}
