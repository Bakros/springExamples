package com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrammyGuitarist implements Singer {

    private static Logger LOGGER = LoggerFactory.getLogger(GrammyGuitarist.class);
    @Override
    public void sing() {
        LOGGER.info("sing: Gravity is working against me and gravity wants to bring me down");
    }
    public void sing(Guitar guitar) {
        LOGGER.info("sing & play: " + guitar.play());
    }
    public void talk(){
        LOGGER.info("talk");
    }
    @Override
    public void rest(){
        LOGGER.info("zzz");
    }

}



