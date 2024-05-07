package com.example.demo.Chapter4.PropertyEditors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Map;

public class CustomPropertyEditorDemo {
    private static Logger LOGGER = LoggerFactory.getLogger(CustomPropertyEditorDemo.class);

    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext(CustomPropertyEditorCfg.class);

        String[] allBeanNames = ctx.getBeanDefinitionNames();
        System.out.println("\n==========Beans managed by ctx configured with @Configurator==========");
        for(String beanName : allBeanNames) {
            System.out.println(beanName + " > "+ ctx.getBean(beanName).getClass());
        }
        System.out.println("\n\n");

        var person = ctx.getBean(Person.class, "person");
        LOGGER.info("Person full nam = {}" , person.getName());

        ctx.close();
    }
}


class FullName {
    private String firstName;
    private String lastName;

    public FullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
//@Component
class Person {
    private FullName name;

    @Value("John Mayer")
    public void setName(FullName name) {
        this.name = name;
    }

    public FullName getName() {
        return name;
    }
}

class NamePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] name = text.split("\\s");
        setValue(new FullName(name[0], name[1]));
    }

    @Override
    public String getAsText() {
        return super.getAsText();
    }
}

@Configuration
//@ComponentScan
class CustomPropertyEditorCfg {
    @Bean
    Person person(){
        return new Person();
    }



    @Bean
    CustomEditorConfigurer customEditorConfigurer(){
        var cust = new CustomEditorConfigurer();
        cust.setCustomEditors(Map.of(FullName.class, NamePropertyEditor.class));
        return cust;
    }

}




