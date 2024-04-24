package com.example.demo.Chapter3.CollectionBean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CollectionExampleStartingPoint {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(CollectionConfig.class, CollectingBean.class);
        ctx.refresh();
        var collectingBean = ctx.getBean(CollectingBean.class);
        collectingBean.printCollections();
    }
}


