package com.example.demo.Chapter4.Profile.Car;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

(public class LoadCar {
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
)