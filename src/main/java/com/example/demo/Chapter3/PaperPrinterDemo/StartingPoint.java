package com.example.demo.Chapter3.PaperPrinterDemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StartingPoint {
    public static void main(String[] args) {


        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfiguratorClass.class);
        Printer newPrinter = ctx.getBean("printerPoint", Printer.class);
        System.out.println(newPrinter.print("Hello World printed with Spring!!!"));

        String[] allBeanNames = ctx.getBeanDefinitionNames();
        System.out.println("\n==========Beans managed by ctx configured with @Configurator==========");
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
        System.out.println("\n\n");


    }
}
