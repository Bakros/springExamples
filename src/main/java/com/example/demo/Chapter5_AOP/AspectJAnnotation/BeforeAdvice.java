package com.example.demo.Chapter5_AOP.AspectJAnnotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BeforeAdvice {
    private static Logger LOGGER = LoggerFactory.getLogger(BeforeAdvice.class);

    /**
     * BeforeAdviceV1
     * @param joinPoint
     */
    /*
    @Before("execution(* com.example.demo.Chapter5_AOP.AspectJAnnotation..sing*(com.example.demo.Chapter5_AOP.AspectJAnnotation.Guitar))")
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" > Executing: {} from {}", signature.getName(), signature.
                getDeclaringTypeName() );
    }*/


    /**
     * BeforeAdviceV2
     */
    /*
    @Pointcut("execution(* com.example.demo.Chapter5_AOP.AspectJAnnotation..sing*(com.example.demo.Chapter5_AOP.AspectJAnnotation.Guitar))")
    public void singExecution(){    }

    @Before("singExecution()")
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" > Executing: {} from {}", signature.getName(), signature.getDeclaringTypeName() );
    }
     */

    /**
     * BeforeAdviceV3 - Compose
     */
    @Pointcut("execution(* com.example.demo.Chapter5_AOP.AspectJAnnotation..sing*(com.example.demo.Chapter5_AOP.AspectJAnnotation.Guitar))")
    public void singExecution(){}

    @Pointcut("bean(john*)")
    public void isJohn(){}

    @Before("singExecution() && isJohn()")
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" > Executing: {} from {}", signature.getName(), signature.getDeclaringTypeName() );
    }


}
