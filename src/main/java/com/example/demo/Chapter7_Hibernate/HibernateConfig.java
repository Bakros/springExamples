package com.example.demo.Chapter7_Hibernate;

import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MariaDB106Dialect;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.util.Properties;


@Import(BasicDataSourceCfg.class)
@Configuration
@ComponentScan
@EnableTransactionManagement
public class HibernateConfig {

    private static Logger LOGGER = LoggerFactory.getLogger(HibernateConfig.class);

    @Value("${spring.jpa.show-sql}")
    private String showSQL;

    @Autowired
    DataSource dataSource;

    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        // hibernateProp.put(Environment.DIALECT, "org.hibernate.dialect.MariaDB106Dialect ");
        hibernateProp.put(Environment.HBM2DDL_AUTO, "none");
        hibernateProp.put(Environment.FORMAT_SQL, false);
        hibernateProp.put(Environment.USE_SQL_COMMENTS, false);
        hibernateProp.put(Environment.SHOW_SQL, false);
        hibernateProp.put(Environment.MAX_FETCH_DEPTH, 3);
        hibernateProp.put(Environment.STATEMENT_BATCH_SIZE, 10);
        hibernateProp.put(Environment.STATEMENT_FETCH_SIZE, 50);
        hibernateProp.put(Environment.SHOW_SQL, showSQL);
        // hibernateProp.put(Environment.JTA_PLATFORM, "org.springframework.orm.hibernate5.ConfigurableJtaPlatform");
        return hibernateProp;
    }

    /**
     * Generation of SessionFactory through the LocalSessionFactoryBean. A Spring related to
     * Hibernate 5.
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.example.demo.Chapter7_Hibernate.Entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    /**
     * The Hibernate session factory requires a transaction
     * manager for transactional data access. Spring provides a transaction manager
     * specifically for Hibernate 5 declared in package org.springframework.orm.
     * hibernate5.HibernateTransactionManager.
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        var transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
