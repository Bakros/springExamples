package com.example.demo.Lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Example how to register an object into the Context Application as a Bean.
 */

public class LabSpring {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Configurator.class);

        // Print the display the context name
        System.out.println("Hello" + ctx.getDisplayName());

        // Print all Annotations related to a class.
        Arrays.stream(Configurator.class.getAnnotations())
                .forEach(System.out::println);



        // Se obtiene el Bean del tipo Mix y se asigna a mixBean (Bean 1).
        // Se imprime el objeto rescatado por Spring.
        var mixBean = ctx.getBean("mix",Mix.class);
        System.out.println("mix > " + mixBean.toString());

        // Se crea un segundo Bean del tipo Mix y se asigna a mixBeverage
        Mix mixBeverage = new Mix();
        mixBeverage.setWaterProperties(new WaterProperties("20","Swetty"));

        // Se imprime el segundo objeto.
        System.out.println("mixBeverage > " + mixBeverage.toString());

        // Se registra el segundo Bean en el ApplicationContext
        DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry) ctx.getAutowireCapableBeanFactory();
        registry.registerSingleton("mixBeverage",mixBeverage);

        // Se imprime el segundo objeto / bean rescatado por ApplicationContext y se valida que el output es el mismo
        System.out.println("mixBeverage from Spring > " + ctx.getBean("mixBeverage",Mix.class).toString());
    }
}

@Configuration
@ComponentScan
class Configurator{}

@Component
class WaterProperties{
    public String phValue;
    public String property;

    public WaterProperties(){
        phValue = "15";
        property = "Tasty";
    }

    public WaterProperties(String phValue, String property){
        this.phValue = phValue;
        this.property = property;
    }

}

@Component
class Mix{
    private WaterProperties waterProperties;

    public WaterProperties getWaterProperties() {
        return waterProperties;
    }

    @Autowired
    public void setWaterProperties(WaterProperties waterProperties) {
        this.waterProperties = waterProperties;
    }
}