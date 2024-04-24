package com.example.demo.Chapter3.BakeryStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BakeStoreStrawberry implements BakeStore{

    private Oven myOven;

    @Override
    public Cake bakeACake() {
        return myOven.bakeALemonCake();
    }

    @Override
    @Autowired
    public void setOven(Oven myOven) {
        this.myOven = myOven;
    }

    @Override
    public Oven getOven() {
        return this.myOven;
    }


}
