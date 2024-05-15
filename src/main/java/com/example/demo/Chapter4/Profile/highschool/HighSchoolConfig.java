package com.example.demo.Chapter4.Profile.highschool;

import com.example.demo.Chapter4.Profile.FoodProviderServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("highschool")
public class HighSchoolConfig {

    @Bean
    FoodProviderServices foodProviderServices(){
        return new FoodProviderServiceImpl();
    }
}
