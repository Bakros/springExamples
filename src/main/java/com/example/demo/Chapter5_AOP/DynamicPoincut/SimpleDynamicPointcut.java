package com.example.demo.Chapter5_AOP.DynamicPoincut;

import com.example.demo.Chapter5_AOP.StaticPoincut.GoodGuitarist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

    private static Logger LOGGER = LoggerFactory.getLogger(SimpleDynamicPointcut.class);


    @Override
    public ClassFilter getClassFilter() {
        return cls -> (cls == GoodGuitarist.class);
    }
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return ("sing".equals(method.getName()));
    }
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        LOGGER.info("Dynamic check for " + method.getName());
        if(args.length == 0) {
            return false;
        }
        var key = (String) args[0];
        return key.equalsIgnoreCase("C");
    }
}