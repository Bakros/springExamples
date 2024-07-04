package com.example.demo.Chapter6.EmbeddedH2Hikari;

import com.example.demo.Chapter6.EmbeddedH2Hikari.repo.SingerRepo;
import com.example.demo.Chapter6.JDBCTemplate.records.Chapter6Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan
public class StartApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(Chapter6Application.class);

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(StartApp.class);

        /*
        // List All beans
        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("Beans in ApplicationContext:");
        for (String beanName : beanNames) {
            System.out.println(beanName + " : " + ctx.getBean(beanName).getClass().getName());
        }
        */

        SingerRepo singerRepo = (SingerRepo) ctx.getBean(SingerRepo.class);
        singerRepo.findAll();
        singerRepo.findAll().forEach(singer -> LOGGER.info(singer.toString()));

    }


}
