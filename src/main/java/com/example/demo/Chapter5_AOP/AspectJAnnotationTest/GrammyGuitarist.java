package com.example.demo.Chapter5_AOP.AspectJAnnotationTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component("johnMayer")
public class GrammyGuitarist implements Singer {

    private static Logger LOGGER = LoggerFactory.getLogger(GrammyGuitarist.class);
    @Override
    public void sing() {
        LOGGER.info("sing: Gravity is working against me and gravity wants to bring me down");
    }
    public void sing(Guitar guitar) {
        LOGGER.info("play: " + guitar.play());
    }
    public void talk(){
        LOGGER.info("talk");
    }
    @Override
    public void rest(){
        LOGGER.info("zzz");
    }

}



