package com.example.demo.Chapter4.Profile.kindergarten;

import com.example.demo.Chapter4.Profile.FoodProviderServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("kindergarten")
public class KindergartenConfig {
    @Bean
    FoodProviderServices foodProviderServices(){
        return new FoodProviderServiceImpl();
    }
}
