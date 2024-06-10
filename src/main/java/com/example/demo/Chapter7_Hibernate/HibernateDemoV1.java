package com.example.demo.Chapter7_Hibernate;

import com.example.demo.Chapter7_Hibernate.DAO.SingerDao;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HibernateDemoV1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateDemoV1.class);
    public static void main(String[] args) {

        var ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");

        //System.out.println(sessionFactory.getProperties().toString());

        var singerDao = ctx.getBean(SingerDao.class);
        LOGGER.info(" ---- Listing singers:");
        singerDao.findAll().forEach(s -> LOGGER.info(s.toString()));
        ctx.close();

        /*
        String[] beanNames = ctx.getBeanDefinitionNames();
        System.out.println("===========  Beans in ApplicationContext: ===========");
        for (String beanName : beanNames) {
            System.out.println(beanName + " : " + ctx.getBean(beanName).getClass().getName());
        }*/


        //Voy en la 354
    }
}
