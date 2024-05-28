package com.example.demo.Chapter5_AOP.AspectJAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(Config.class, BeforeAdviceV1.class);
        ctx.refresh();


        Arrays.asList(ctx.getBeanDefinitionNames()).contains("beforeAdviceV1");
        NewDocumentarist documentarist = ctx.getBean("documentarist",NewDocumentarist.class);
        documentarist.execute();
        ctx.close();
    }
}
