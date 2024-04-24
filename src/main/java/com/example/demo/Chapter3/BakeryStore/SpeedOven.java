package com.example.demo.Chapter3.BakeryStore;

import org.springframework.stereotype.Component;

@Component
public class SpeedOven implements Oven{
    @Override
    public Cake bakeALemonCake() {
        Cake myCake = new Cake();
        myCake.setFlavour("Lemon");
        return myCake;
    }
}
