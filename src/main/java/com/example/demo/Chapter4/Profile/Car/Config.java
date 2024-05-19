package com.example.demo.Chapter4.Profile.Car;

import com.example.demo.Chapter4.Profile.Car.CarBase;
import com.example.demo.Chapter4.Profile.Car.NormalCar;
import com.example.demo.Chapter4.Profile.Car.RacingCar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class Config {
    //Load this Bean into the context only if "slow" is between the active profiles.
    @Profile("slow")
    @Bean
    public CarBase slowCar(){
        return new NormalCar();
    }

    //Load this Bean into the context only if "fast" is between the active profiles.
    @Profile("fast")
    @Bean
    public CarBase fastCar(){
        return new RacingCar();
    }
}
