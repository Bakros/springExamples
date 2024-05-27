package com.example.demo.Chapter5_AOP.DynamicPoincut;

import com.example.demo.Chapter5_AOP.StaticPoincut.GoodGuitarist;
import com.example.demo.Chapter5_AOP.StaticPoincut.SimpleAroundAdvice;
import com.example.demo.Chapter5_AOP.StaticPoincut.Singer;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class DynamicPointcutDemo {
    public static void main(String[] args) {
        GoodGuitarist target = new GoodGuitarist();
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new
                SimpleAroundAdvice());
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target);
        pf.addAdvisor(advisor);
        Singer proxy = (Singer)pf.getProxy();
        proxy.sing("C");
        proxy.sing("c");
        proxy.sing("E");
        proxy.sing();

        AspectJExpressionPointcut test = new AspectJExpressionPointcut();
    }
}
