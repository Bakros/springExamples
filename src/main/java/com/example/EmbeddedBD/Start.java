package com.example.EmbeddedBD;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class Start {

    @Autowired
    public EntityManager entityManager;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Start.class, args);

        System.out.println("DataSource "+context.getBean("getDataSource", DataSource.class).getClass().getName());


    }
}
