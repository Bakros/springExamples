package com.example.demo.Chapter4.Testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ActiveProfiles("test")
@SpringJUnitConfig(classes = {Config.class, TestConfig.class})
public class ExampleTest {

    @Autowired
    House myHouse;

    @Test
    void testConfig() {
        assertAll("messageTest",
                () -> assertNotNull(myHouse)
        );
         myHouse.getAllRooms();
    }
}

@Configuration
class TestConfig{

    @Profile("test")
    @Bean
    public List<Room> rooms(){
        return List.of(new Room("Restroom", "Seba's Restroom"), new Room("Kitchen", "Kitchen"), new Room("LivingRoom", "LivingRoom"));
    }
}