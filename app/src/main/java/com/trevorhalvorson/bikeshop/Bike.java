package com.trevorhalvorson.bikeshop;

import java.io.Serializable;

/**
 * Created by Trevor Halvorson on 3/2/2016.
 */
public class Bike implements Serializable {
    private String brand;
    private String model;
    private double price;

    public Bike(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImageID() {
        return R.drawable.image_bike;
    }
}
