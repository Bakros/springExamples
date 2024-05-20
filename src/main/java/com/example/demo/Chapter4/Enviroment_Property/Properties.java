package com.example.demo.Chapter4.Enviroment_Property;

import com.example.demo.Chapter2.StandardOutMessageRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Properties {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ConfigurableEnvironment configurableEnvironment = ctx.getEnvironment();

        MutablePropertySources mutablePropertySources = configurableEnvironment.getPropertySources();

        System.out.println("\nPrint the PropertySource origin!!!!");
        Iterator it = configurableEnvironment.getPropertySources().iterator();
        while(it.hasNext()){
            PropertySource myPropertySource = (PropertySource) it.next();

            System.out.println(myPropertySource.toString());
        }

        System.out.println("\nPrint all the PropertySource in a unstructured way!!");
        List<PropertySource<?>> list = configurableEnvironment.getPropertySources().stream().toList();
        for (PropertySource ps: list) {
            System.out.println(ps.getSource().toString());
            System.out.println("Another line");
        }

        System.out.println("\nPrint all the PropertySource in a structured way!!");
        Map<String, Object> properties = new HashMap<>();
        for (PropertySource<?> myPropertySource : configurableEnvironment.getPropertySources()) {
            if (myPropertySource instanceof org.springframework.core.env.MapPropertySource) {
                properties.putAll(((org.springframework.core.env.MapPropertySource) myPropertySource).getSource());
            }
        }

        Iterator<Map.Entry<String, Object>> iterator = properties.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        System.out.println("\nSize!!");
        System.out.println("Size > " + ctx.getEnvironment().getPropertySources().size());

        System.out.println("\nAccess an specific Property - USERNAME!!");
        List<PropertySource<?>> list2 = configurableEnvironment.getPropertySources().stream().toList();
        for (PropertySource ps: list2) {
            System.out.println(ps.getName());
            System.out.println(ps.containsProperty("USERNAME"));
            System.out.println(ps.getProperty("USERNAME")+"\n");

        }




        System.out.println("Getting info from properties files ");
        AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext(Config.class);
        MyApp myApp = ctx2.getBean("myApp", MyApp.class);


        System.out.println("\nSize!!");
        System.out.println("Size > " + ctx2.getEnvironment().getPropertySources().size());

        System.out.println("\nPrint the PropertySource origin!!!!");
        Iterator it2 = ctx2.getEnvironment().getPropertySources().iterator();
        while(it2.hasNext()){
            PropertySource myPropertySource = (PropertySource) it2.next();

            System.out.println(myPropertySource.toString());
        }

        System.out.println("\nPropertySources data unstructured way!!!");
        List<PropertySource<?>> list3 = ctx2.getEnvironment().getPropertySources().stream().toList();
        for (PropertySource ps: list3) {
            System.out.println(ps.getSource().toString());
            System.out.println("Another line");
        }

        System.out.println("Example > " + myApp.getAppName());
        System.out.println("Example > " + myApp.getGreeting());
    }
}

@Configuration
// Nota: Se debe especificar el package completo ya que ya se está haciendo uso de
// org.springframework.core.env.PropertySource; en el import.
@org.springframework.context.annotation.PropertySource("classpath:myOwnInfo.properties")
@org.springframework.context.annotation.PropertySource("classpath:application.properties")
class Config{

    @Bean
    public MyApp myApp(){
        return new MyApp();
    }

}

class MyApp{
    @Value("${spring.application.name}")
    String appName;

    @Value("${app.greeting}")
    String greeting;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public MyApp() {
    }
}
