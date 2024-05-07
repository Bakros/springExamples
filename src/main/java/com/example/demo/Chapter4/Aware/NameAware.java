package com.example.demo.Chapter4.Aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;


public class NameAware {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AwareConfigNameAware.class);
        ctx.registerShutdownHook();
        var singer = ctx.getBean(NamedSinger.class);
        singer.sing();


        ctx.getBeanFactory();

    }

}
/**
 * This class implements BeanNameAware that lets the Bean to access to its own name
 */
class NamedSinger implements BeanNameAware {
    private static Logger logger = LoggerFactory.getLogger(NamedSinger.class);
    private String name;
    @Override /** @Implements {@link BeanNameAware#setBeanName(String)} */
    public void setBeanName(String beanName) {
        this.name = beanName;
    }
    public void sing() {
        logger.info("Singer " + name + " - sing()");
    }
}
@ComponentScan
class AwareConfigNameAware {
    @Bean
    NamedSinger johnMayer(){
        return new NamedSinger();
    }
}