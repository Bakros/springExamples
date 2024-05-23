package com.example.demo.Chapter5_AOP.FirstExample;

import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;

public class ManualAdviceDemo {
    public static void main(String[] args) {
        Concert concert = new Concert();
        concert.execute();
        concert.afterShow();

        LoggerFactory.getLogger(Concert.class).info("Newwww test");

        ProxyFactory pf = new ProxyFactory();
        pf.addAdvice(new SimpleBeforeAdvice());
        pf.addAdvice(new SimpleAroundAdvice());
        pf.addAdvice(new SimpleAfterAdvice());
        pf.setTarget(concert);

        Performance proxy = (Performance) pf.getProxy();
        proxy.execute();
        proxy.afterShow();

    }
}
