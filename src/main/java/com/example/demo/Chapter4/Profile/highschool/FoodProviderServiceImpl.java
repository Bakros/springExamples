package com.example.demo.Chapter4.Profile.highschool;

import com.example.demo.Chapter4.Profile.Food;
import com.example.demo.Chapter4.Profile.FoodProviderServices;

import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderServices {
    @Override
    public List<Food> provideLunchSet() {
        return List.of(new Food("Coke"), new Food("Hamburguers"), new Food("Fries"));
    }
}
