package com.example.demo.Chapter7_Hibernate;

import com.example.demo.Chapter7_Hibernate.DAO.SingerDao;
import com.example.demo.Chapter7_Hibernate.Entities.Singer;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.util.List;


/**
 * Hibernate example using installed MariaDB in localhost
 */
public class HibernateDemoV1 {
    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateDemoV1.class);
    public static void main(String[] args) {

        var ctx = new AnnotationConfigApplicationContext(HibernateConfig.class);

        SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");


        System.out.println("Connection Pool > " + ctx.getBean("dataSource", DataSource.class).getClass().getName());

        //System.out.println(sessionFactory.getProperties().toString());

        var singerDao = ctx.getBean(SingerDao.class);
        LOGGER.info(" ---- Listing singers:");
        singerDao.findAll().forEach(s -> LOGGER.info(s.toString()));


        var singer = singerDao.findById(2L);
        System.out.println(singer.getFirstName());

        LOGGER.info("Call the NamedQuery and list it");
        List<Singer> listSinger = singerDao.findAllWithAlbum();

        for (Singer singerUnit : listSinger) {
            LOGGER.info(singerUnit.toString());

            singerUnit.getAlbums().forEach(s -> LOGGER.info(s.toString()));
        }

        LOGGER.info("Call the NamedQuery and list it");


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
