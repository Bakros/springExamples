package com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2;

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
    @Before("execution(* com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2..sing*(com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2.Guitar))")
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" > Executing: {} from {}", signature.getName(), signature.
                getDeclaringTypeName() );
    }*/


    /**
     * BeforeAdviceV2
     */
    /*
    @Pointcut("execution(* com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2..sing*(com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2.Guitar))")
    public void singExecution(){    }

    @Before(value = "singExecution()", argNames = "joinPoint")
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" > Executing: {} from {}", signature.getName(), signature.getDeclaringTypeName() );
    }
    */

    /**
     * BeforeAdviceV3 - Compose
     */
    /*
    @Pointcut("execution(* com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2..sing*(com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2.Guitar))")
    public void singExecution(){}

    @Pointcut("bean(john*)")
    public void isJohn(){}

    //@Before(value = "singExecution() && isJohn()")
    @Before(value = "singExecution() && isJohn()", argNames = "joinPoint")
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" Before Advise! > Executing: {} from {}", signature.getName(), signature.getDeclaringTypeName() );
    }
     */



    /**
     * BeforeAdviceV3 - Compose with arguments.
     */

    @Pointcut("execution(* com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2..sing*" +
            "(com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2.Guitar)) && args(value)")
    public void singExecution(Guitar value){}

    @Pointcut("bean(john*)")
    public void isJohn(){}

    @Before(value = "singExecution(guitar) && isJohn()", argNames = "joinpoint, guitar")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar guitar) {
        var signature = (MethodSignature) joinPoint.getSignature();
        LOGGER.info(" Before Advise! > Executing: {} from {}", signature.getName(), signature.getDeclaringTypeName() );

        if(guitar.getBrand().equals("Gibson")) {
            LOGGER.info(" Before Advise! > Executing Gibson: {} from {}", signature.getName(), signature.
                    getDeclaringTypeName());
        }
    }


    /**
     * Test Advice that apply to every public method.
     * Regardless of the return type or the method's parameters.
     */
    /*
    @Before("execution(public * *(..))")
    public void testBefore(JoinPoint joinPoint){
        var signature = (MethodSignature) joinPoint.getSignature();


        System.out.println("I'm executing...");
        LOGGER.info(" I'm executing...with Looger - {} from {}", signature.getName(), signature.getDeclaringTypeName() );
    }
     */


}
