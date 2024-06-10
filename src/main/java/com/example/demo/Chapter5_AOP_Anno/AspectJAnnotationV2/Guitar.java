package com.example.demo.Chapter5_AOP_Anno.AspectJAnnotationV2;

class Guitar {
    private String brand =" Martin";

    public String play(){
        return "G C G C Am D7";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Guitar(String brand) {
        this.brand = brand;
    }

    public Guitar() {
    }
}