package com.example.demo.Chapter7_Hibernate;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");

        System.out.println(sessionFactory.getProperties().toString());

        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("===========  Beans in ApplicationContext: ===========");
        for (String beanName : beanNames) {
            System.out.println(beanName + " : " + ctx.getBean(beanName).getClass().getName());
        }


    }
}
