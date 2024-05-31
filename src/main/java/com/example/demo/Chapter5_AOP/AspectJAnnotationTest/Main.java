package com.example.demo.Chapter5_AOP.AspectJAnnotationTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/***
 *  A newDocumentarist includes as a member a GrammyGuitarrist object.
 *  A GrammyGuitarrist implements the Singer interface. It also has a method sing that receives a Guitar a parameter.
 *
 *  newDocumentaris -> GrammyGuitarrist -> Guitar
 */


public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class, BeforeAdvice.class);
        ctx.refresh();

        NewDocumentarist newDocumentarist = new NewDocumentarist(
                (GrammyGuitarist) ctx.getBean("johnMayer", GrammyGuitarist.class),
                new Guitar("Gibson"));

        newDocumentarist.execute();

       LoggerFactory.getLogger(Main.class).info("Changing the assignation!");

        newDocumentarist = new NewDocumentarist(
                (GrammyGuitarist) ctx.getBean("eltonMayer", GrammyGuitarist.class),
                new Guitar("Fender"));

        newDocumentarist.execute();

        LoggerFactory.getLogger(Main.class).info("===================================================");
        displayAllBeans(ctx);

        ctx.close();

    }

    public static void displayAllBeans(ApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();
        System.out.println("Beans in ApplicationContext:");
        for (String beanName : beanNames) {
            System.out.println(beanName + " : " + context.getBean(beanName).getClass().getName());
        }
    }
}
