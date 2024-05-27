package com.example.demo.Chapter5_AOP.StaticPoincut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleAroundAdvice implements MethodInterceptor {
    private static Logger LOGGER = LoggerFactory.getLogger(GoodGuitarist.class);
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        LOGGER.info(">> Invoking " + invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        LOGGER.info(">> Done");

        return retVal;
    }
}