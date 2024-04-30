package com.example.demo.Chapter3.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SetterExample {
    private String someValue;

    /*
    public SetterExample(@Value("90") int someValue) {
        System.out.println("ConstructorConfusion(int) called");
        this.someValue = "Number: " + Integer.toString(someValue);
    }
     */

    public SetterExample(){}

    // In this case @Autowired is mandatory because is this in here the member
    // someValue is null. The object could be created without the member.
    @Autowired
    public void setSomeValue(@Value("90") int someValue) {
        System.out.println("SetterCalled(int) called");
        this.someValue = "Number: " + Integer.toString(someValue);
    }

    public String toString() {
        return someValue;
    }

    public static void main(String... args) {
        //No parameter was added to AnnotationConfigApplicationContext() because it is the same class.
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(SetterExample.class);
        ctx.refresh();
        var cc = ctx.getBean(SetterExample.class);
        System.out.println("Does this work? " + cc);

        String[] allBeanNames = ctx.getBeanDefinitionNames();
        System.out.println("\n==========Beans managed by ctx before Destroying ALL==========");
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }
}
