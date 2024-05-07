package com.example.demo.Chapter3.BakeryStore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BakerStaringPoint {
    public static void main(String[] args) {
        ApplicationContext ctx_baker = new AnnotationConfigApplicationContext(BakeConfigurator.class);
        BakeStore bakerStore = ctx_baker.getBean("bakeStoreStrawberry", BakeStoreStrawberry.class);
        System.out.println("Cake ready! - Flavour > " + bakerStore.bakeACake().getFlavour());

        String[] allBeanNamesFromBaker = ctx_baker.getBeanDefinitionNames();
        System.out.println("\n==========Beans managed by ctx configured with @ComponentScan==========");
        for(String beanName : allBeanNamesFromBaker) {
            System.out.println(beanName);
        }
    }
}
