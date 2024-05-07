package com.example.demo.Chapter4.Aware;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Example of ApplicationContextAware and Destroy ShuthookDown - Page 125 Chapter 4.
 */

public class AwareDemo_StartingPoint{
    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AwareConfig.class);

        ConfigurableApplicationContext test = (ConfigurableApplicationContext) ctx;
        test.registerShutdownHook();
        test.close();
    }
}

/*
Through the implementation of ApplicationContextAware the object resulting of the instantiation of the class
ShutdownHookBean will have access to the ApplicationContext where that object is managed as a Bean.
In this case the access to ApplicationContext is used to access the
 */
class ShutdownHookBean implements ApplicationContextAware {
    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        if(ctx instanceof GenericApplicationContext){
            ((GenericApplicationContext) ctx).registerShutdownHook();
        }
    }
}

@ComponentScan
class AwareConfig{

    @Bean
    FileManager fileManager(){
        return new FileManager();
    }

    @Bean
    ShutdownHookBean shutdownHookBean(){
        return new ShutdownHookBean();
    }
}
class FileManager {
    private static Logger logger = LoggerFactory.getLogger(FileManager.class);
    private Path file;

    public FileManager() {
        logger.info("Creating bean of type {}", FileManager.class);
        try {
            file = Files.createFile(Path.of("sample"));
        } catch (IOException e) {
            logger.error("Could not create file");
        }
    }

    @PreDestroy
    private void preDestroy() throws IOException {
        logger.info("Calling preDestroy() on bean of type {}", FileManager.class);
        if (file != null) {
            Files.deleteIfExists(file);
        }
    }
}