package com.example.demo.Chapter6.EmbeddedH2HikariV2;

import com.example.demo.Chapter6.EmbeddedH2HikariV2.repo.SingerJdbcRepo;
import com.example.demo.Chapter6.EmbeddedH2HikariV2.repo.SingerRepo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan
public class StartApp {

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(StartApp.class);

        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate", JdbcTemplate.class);

        SingerRepo singerRepo = (SingerRepo) ctx.getBean("singerRepo", SingerJdbcRepo.class);

        singerRepo.findFirstNameById(1L);

        System.out.println("DataSource used: " + jdbcTemplate.getDataSource().getClass().getName());


    }
}
