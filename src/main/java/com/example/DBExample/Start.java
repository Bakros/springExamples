package com.example.DBExample;

import ch.qos.logback.classic.LoggerContext;
import com.example.DBExample.Entities.Singer;
import com.example.DBExample.Services.AllServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class Start {
    private static final Logger LOGGER = LoggerFactory.getLogger(Start.class);

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Start.class, args);

        //System.out.println("Loogiiing  > "+ String.valueOf(LOGGER.isDebugEnabled()));

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        for (ch.qos.logback.classic.Logger logger : loggerContext.getLoggerList()) {
            if(logger.getLevel() != null)
                System.out.println("Logger: " + logger.getName() + ", Level: " + logger.getLevel());
        }


        //ch.qos.logback.classic.Logger specificLogger = loggerContext.getLogger("com.example.DBExample.Start");
        //System.out.println("Specific Logger: " + specificLogger.getName() + ", Level: " + specificLogger.getLevel());


        AllServices allServices = ctx.getBean(AllServices.class);

        //Find just one
        /*
        Optional<Singer> optionalSinger = allServices.findOne(1L);
        if(optionalSinger.isPresent()) {
            System.out.println(optionalSinger.get().toString());
        }else{
            System.out.println("Nope - zip - none!");
        }
        */

        allServices.findAll().forEach(s -> System.out.println(s.toString()));
    }
}
