package com.example.glicemicloadcalculator;

import java.io.Serializable;

public class Product implements Serializable {
    String name;
    String glycemicIndex;
    String carbohydrates;
    String fiber;

    public Product(String name, String glycemicIndex, String carbohydrates, String fiber){
        this.name = name;
        this.glycemicIndex = glycemicIndex;
        this.carbohydrates = carbohydrates;
        this.fiber = fiber;
    }

    public String getGlycemicIndex() {
        return glycemicIndex;
    }

    public void setGlycemicIndex(String glycemicIndex) {
        this.glycemicIndex = glycemicIndex;
    }

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getFiber() {
        return fiber;
    }

    public void setFiber(String fiber) {
        this.fiber = fiber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

