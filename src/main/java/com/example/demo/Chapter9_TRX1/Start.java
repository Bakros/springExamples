package com.example.demo.Chapter9_TRX1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Start {

    public static void main(String[] args) {
        ApplicationContext ctx =  new AnnotationConfigApplicationContext(Start.class);

        System.out.println("Hello");
    }
}
