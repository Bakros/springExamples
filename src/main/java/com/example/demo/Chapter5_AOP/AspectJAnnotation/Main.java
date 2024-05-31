package com.example.demo.Chapter5_AOP.AspectJAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/***
 *  A newDocumentarist includes as a member a GrammyGuitarrist object.
 *  A GrammyGuitarrist implements the Singer interface. It also has a method sing that receives a Guitar a parameter.
 *
 *  newDocumentaris -> GrammyGuitarrist -> Guitar
 */


public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class, BeforeAdvice.class);
        ctx.refresh();

        Arrays.asList(ctx.getBeanDefinitionNames()).contains("beforeAdviceV1");
        NewDocumentarist documentarist = ctx.getBean("documentarist",NewDocumentarist.class);
        documentarist.execute();
        ctx.close();
    }
}
