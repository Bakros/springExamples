package com.example.demo.Chapter3.Autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ConstructorExample {
    private String someValue;

    // @Autowired is not defined. Is the class has only one Constructor
    // the @Autowired is not necessary.
    public ConstructorExample(@Value("90") int someValue) {
        System.out.println("ConstructorConfusion(int) called");
        this.someValue = "Number: " + Integer.toString(someValue);
    }
    public String toString() {
        return someValue;
    }
    public static void main(String... args) {
        //No parameter was added to AnnotationConfigApplicationContext() because it is the same class.
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(ConstructorExample.class);
        ctx.refresh();
        var cc = ctx.getBean(ConstructorExample.class);
        System.out.println("Does this work? " + cc);
    }
}
