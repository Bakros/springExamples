package com.example.demo.Chapter4.Profile.Car;

public class NormalCar implements CarBase{
    Integer speed =0;

    @Override
    public Integer speedUp() {
        speed =+5;
        return speed;
    }

    @Override
    public Integer slowDown() {
        speed =-5;
        speed = (speed < 0) ? 0 : speed;
        return speed;
    }
}
