package com.example.demo.Chapter4.Profile;

import com.example.demo.Chapter4.Profile.Car.CarBase;
import com.example.demo.Chapter4.Profile.Car.Config;
import com.example.demo.Chapter4.Profile.highschool.HighSchoolConfig;
import com.example.demo.Chapter4.Profile.kindergarten.KindergartenConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;


public class ProfileDemo {
    private static Logger logger = LoggerFactory.getLogger(ProfileDemo.class);

    public static void main(String... args) {
        var profile= System.getProperty("spring.profiles.active");
        var ctx = new AnnotationConfigApplicationContext();

        profile = "kindergarten";
       // profile = "highschool";


        /**
         * From Spring Documentation > https://github.com/spring-projects/spring-framework/blob/6.1.x/spring-core/src/main/java/org/springframework/core/env/ConfigurableEnvironment.java

         * Specify the set of profiles active for this {@code Environment}. Profiles are
         * evaluated during container bootstrap to determine whether bean definitions
         * should be registered with the container.
         * <p>Any existing active profiles will be replaced with the given arguments; call
         * with zero arguments to clear the current set of active profiles. Use
         * {@link #addActiveProfile} to add a profile while preserving the existing set.
         * @throws IllegalArgumentException if any profile is null, empty or whitespace-only
         * @see #addActiveProfile
         * @see #setDefaultProfiles
         * @see org.springframework.context.annotation.Profile
         * @see AbstractEnvironment#ACTIVE_PROFILES_PROPERTY_NAME
         	void setActiveProfiles(Stri ng... profles);
         */
        //ctx.getEnvironment().setActiveProfiles(profile);
        ctx.getEnvironment().setActiveProfiles("kindergarten","fast");

        var environment = ctx.getEnvironment();

        System.out.println(" ===== Active Profiles ===== ");
        Arrays.stream(ctx.getEnvironment().getActiveProfiles()).forEach(System.out::println);

        System.out.println("===== Default Profiles =====");
        Arrays.stream(ctx.getEnvironment().getDefaultProfiles()).forEach(System.out::println);

        ctx.register(HighSchoolConfig.class, KindergartenConfig.class, Config.class);
        //ctx.register(Config.class);
        ctx.refresh();

        var myCar = ctx.getBean(CarBase.class);

        logger.info(myCar.toString());

        var foodProviderService = ctx.getBean("foodProviderServices", FoodProviderServices.class);
        var lunchSet = foodProviderService.provideLunchSet();
        lunchSet.forEach(food -> logger.info("Food: {}", food.getName()));


        ctx.close();
    }

}
