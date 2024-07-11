package com.example.demo.Chapter9_TRX1;

import com.example.demo.Chapter9_TRX1.entities.Singer;
import com.example.demo.Chapter9_TRX1.repos.SingerRepoImpl;
import com.example.demo.Chapter9_TRX1.services.AllService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:myOwnInfo.properties")
public class Start {
    private static Logger LOGGER = LoggerFactory.getLogger(Start.class);


    public static void main(String[] args) {
        ApplicationContext ctx =  new AnnotationConfigApplicationContext(Start.class);

        System.out.println("Hello");

        LOGGER.info("Test");

        var singerRepoImpl  = ctx.getBean(SingerRepoImpl.class);
        Singer singer = singerRepoImpl.findOneSinger(1);
        LOGGER.info(singer.toString());

        /*
        var service = ctx.getBean(AllService.class);

        LOGGER.info(" ---- Listing singers:");
        service.findAllWithAlbums().forEach(s -> LOGGER.info(s.toString()));
        */


    }
}
