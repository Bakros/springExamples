package com.example.demo.Chapter3;

import com.example.demo.Chapter3.BakeryStore.BakeStore;
import com.example.demo.Chapter3.BakeryStore.BakeConfigurator;
import com.example.demo.Chapter3.BakeryStore.BakeStoreStrawberry;
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

        ApplicationContext ctx_baker = new AnnotationConfigApplicationContext(BakeConfigurator.class);
        BakeStore bakerStore = ctx_baker.getBean("bakeStoreStrawberry", BakeStoreStrawberry.class);
        System.out.println("Cake ready! - Flavour > " + bakerStore.bakeACake().getFlavour());

        String[] allBeanNamesFromBaker = ctx_baker.getBeanDefinitionNames();
        System.out.println("\n==========Beans managed by ctx configured with @ComponentScan==========");
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }
}
