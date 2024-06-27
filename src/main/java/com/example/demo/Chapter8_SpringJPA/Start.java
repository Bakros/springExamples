package com.example.demo.Chapter8_SpringJPA;

import com.example.HTMLTableDemo.DemoJavaApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Start {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DemoJavaApplication.class, args);

    }
}
