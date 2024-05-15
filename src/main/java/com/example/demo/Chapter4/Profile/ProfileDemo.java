package com.example.demo.Chapter4.Profile;

import com.example.demo.Chapter4.Profile.highschool.HighSchoolConfig;
import com.example.demo.Chapter4.Profile.kindergarten.KindergartenConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ProfileDemo {
    private static Logger logger = LoggerFactory.getLogger(ProfileDemo.class);

    public static void main(String... args) {
        var profile= System.getProperty("spring.profiles.active");
        var ctx = new AnnotationConfigApplicationContext();

        profile = "kindergarten";
        //profile = "highschool";

        ctx.getEnvironment().setActiveProfiles(profile);

        ctx.getEnvironment().getActiveProfiles();

        ctx.register(HighSchoolConfig.class, KindergartenConfig.class);
        ctx.refresh();

        var foodProviderService = ctx.getBean("foodProviderServices", FoodProviderServices.class);
        var lunchSet = foodProviderService.provideLunchSet();
        lunchSet.forEach(food -> logger.info("Food: {}", food.getName()));
        ctx.close();
    }
}
