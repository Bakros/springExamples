package com.example.demo.Chapter4.Profile.kindergarten;

import com.example.demo.Chapter4.Profile.Food;
import com.example.demo.Chapter4.Profile.FoodProviderServices;

import java.util.List;

public class FoodProviderServiceImpl implements FoodProviderServices {
    @Override
    public List<Food> provideLunchSet() {
        return List.of(new Food("Milk"), new Food("Biscuits"));
    }
}
