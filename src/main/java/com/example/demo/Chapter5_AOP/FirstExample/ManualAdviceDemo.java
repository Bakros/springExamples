package com.example.demo.Chapter5_AOP.FirstExample;

import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;

public class ManualAdviceDemo {
    public static void main(String[] args) {
        // New object (original one) and execute the methods execute() and aftershow();
        Concert concert = new Concert();
        concert.execute();
        concert.afterShow();

        LoggerFactory.getLogger(Concert.class).info("\nFrom now on the Proxy is working\n");

        //New ProxyFactory
        ProxyFactory pf = new ProxyFactory();

        //Add Advises
        pf.addAdvice(new SimpleBeforeAdvice());
        pf.addAdvice(new SimpleAroundAdvice());
        pf.addAdvice(new SimpleAfterAdvice());

        //Set Target object where the Advises will apply
        pf.setTarget(concert);

        //Get the proxy and execute the same methods.
        Performance proxy = (Performance) pf.getProxy();
        proxy.execute();
        proxy.afterShow();
    }
}
