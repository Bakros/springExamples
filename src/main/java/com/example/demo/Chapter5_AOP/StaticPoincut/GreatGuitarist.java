package com.example.demo.Chapter5_AOP.StaticPoincut;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreatGuitarist implements Singer {
    private static Logger LOGGER = LoggerFactory.getLogger(GoodGuitarist.class);

    @Override
    public void sing() {
        LOGGER.info("You've got my soul in your hand");
    }
}
