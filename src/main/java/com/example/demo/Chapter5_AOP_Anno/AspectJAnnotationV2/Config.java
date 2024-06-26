package com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Config {

    @Bean
    public GrammyGuitarist johnMayer(){
        return new GrammyGuitarist();
    }

    @Bean
    public GrammyGuitarist eltonMayer(){
        return new GrammyGuitarist();
    }

    @Bean
    public GrammyGuitarist johnMinor(){
        return new GrammyGuitarist();
    }



}
