package com.example.demo.Chapter4.Profile.Car;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LoadCar {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        //ctx.getEnvironment().setActiveProfiles("slow","fast");
        ctx.getEnvironment().setActiveProfiles("slow");

        ctx.register(Config.class);
        ctx.refresh();

        CarBase car = ctx.getBean(CarBase.class);
        System.out.println(car.toString());
    }
}