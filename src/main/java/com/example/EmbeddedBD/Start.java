package com.example.EmbeddedBD;

import com.example.EmbeddedBD.model.Contact;
import com.example.EmbeddedBD.repository.ContactRepository;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

@SpringBootApplication
public class Start {
    private static Logger LOGGER = LoggerFactory.getLogger(Start.class);

    @Autowired
    public EntityManager entityManager;

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Start.class, args);

        System.out.println("DataSource "+ctx.getBean("getDataSource", DataSource.class).getClass().getName());

        ContactRepository contactRepository = (ContactRepository) ctx.getBean(ContactRepository.class);

        contactRepository.save(new Contact("Seba", "87458745", "hola@hola.cl"));

        contactRepository.findAll().forEach(singer -> LOGGER.info(singer.toString()));


    }
}
