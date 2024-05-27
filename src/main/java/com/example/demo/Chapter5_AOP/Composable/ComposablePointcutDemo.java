package com.example.demo.Chapter5_AOP.Composable;

import com.example.demo.Chapter5_AOP.FirstExample.SimpleBeforeAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

import java.lang.reflect.Method;

public class ComposablePointcutDemo {

    private static Logger LOGGER = LoggerFactory.getLogger(ComposablePointcutDemo.class);

    public static void main(String[] args) {
        GrammyGuitarist johnMayer = new GrammyGuitarist();
        ComposablePointcut pc = new ComposablePointcut(ClassFilter.TRUE, new SingMethodMatcher());
        LOGGER.info("Test 1 >> ");
        GrammyGuitarist proxy = getProxy(pc, johnMayer);
        testInvoke(proxy);

        LOGGER.info("Test 2 >> ");
        pc.union(new TalkMethodMatcher());
        proxy = getProxy(pc, johnMayer);
        testInvoke(proxy);

        LOGGER.info("Test 3 >> ");
        pc.intersection(new RestMethodMatcher());
        proxy = getProxy(pc, johnMayer);
        testInvoke(proxy);
    }

    private static GrammyGuitarist getProxy(ComposablePointcut pc, GrammyGuitarist target) {
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        return (GrammyGuitarist) pf.getProxy();
    }
    private static void testInvoke(GrammyGuitarist proxy) {
        proxy.sing();
        proxy.sing(new Guitar());
        proxy.talk();
        proxy.rest();
    }
}

class SingMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> cls) {
        return (method.getName().startsWith("si"));
    }
}
class RestMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> cls) {
        return (method.getName().endsWith("st"));
    }
}
class TalkMethodMatcher extends StaticMethodMatcher {
    @Override
    public boolean matches(Method method, Class<?> cls) {
        return "talk".equals(method.getName());
    }
}

interface Singer {
    Logger LOGGER = LoggerFactory.getLogger(Singer.class);

    void sing();

    default void sing(String key){
        LOGGER.info("Singing in the key of {}", key);
    }

    default void rest(){
    }
}

class GrammyGuitarist implements Singer {
    private static Logger LOGGER = LoggerFactory.getLogger(GrammyGuitarist.class);

    @Override
    public void sing() {
        LOGGER.info("sing: Gravity is working against me\n" +
                "And gravity wants to bring me down");
    }

    public void sing(Guitar guitar) {
        LOGGER.info("play: " + guitar.play());
    }

    public void talk() {
        LOGGER.info("talk");
    }

    @Override
    public void rest() {
        LOGGER.info("zzz");
    }
}

class Guitar {
    private String brand =" Martin";

    public String play(){
        return "G C G C Am D7";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}