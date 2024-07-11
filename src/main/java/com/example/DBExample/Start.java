package com.example.DBExample;

import com.example.DBExample.Entities.Singer;
import com.example.DBExample.Services.AllServices;
import com.example.demo.Chapter2.StandardOutMessageRenderer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class Start {
    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(Start.class, args);

        AllServices allServices = ctx.getBean(AllServices.class);


        Optional<Singer> optionalSinger =   allServices.findOne(1L);

        if(optionalSinger.isPresent()) {
            System.out.println(optionalSinger.get().toString());
        }else{
            System.out.println("Nope - zip - none!");
        }

    }
}
