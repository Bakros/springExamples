package com.example.demo.Chapter4.Profile.Car;

public class RacingCar implements CarBase{
    Integer speed =0;

    @Override
    public Integer speedUp() {
        speed =+10;
        return speed;
    }

    @Override
    public Integer slowDown() {
        speed =-10;
        speed = (speed < 0) ? 0 : speed;
        return speed;
    }
}
