package com.example.demo.Chapter5_AOP.FirstExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {
    private static Logger logger = LoggerFactory.getLogger(SimpleBeforeAdvice.class);
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        logger.info("Before: set up concert hall.");
    }
}
