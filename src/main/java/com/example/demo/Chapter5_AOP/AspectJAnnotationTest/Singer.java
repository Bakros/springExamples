package com.example.demo.Chapter5_AOP.AspectJAnnotationTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

interface Singer {
    Logger LOGGER = LoggerFactory.getLogger(Singer.class);

    void sing();

    default void sing(String key){
        LOGGER.info("Singing in the key of {}", key);
    }

    default void rest(){
    }
}