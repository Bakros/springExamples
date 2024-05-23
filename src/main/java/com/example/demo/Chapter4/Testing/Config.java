package com.example.demo.Chapter4.Testing;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
public class Config {

    @Profile("dev")
    @Bean
    public List<Room> rooms(){
        return List.of(new Room("Bedroom", "Seba's room"), new Room("Bathroom", "Main Bathroom"), new Room("LivingRoom", "LivingRoom"));
    }

    @Bean
    public House myHouse(){
        return new House();
    }
}
