package com.example.demo.Chapter5_AOP.FirstExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class SimpleAfterAdvice implements AfterReturningAdvice {
    private static Logger logger = LoggerFactory.getLogger(SimpleAfterAdvice.class);
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object
            target) throws Throwable {
        logger.info("After: offer standing ovation.");
    }
}
