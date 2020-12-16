package com.example.case1_amonicairlines;

public class Amenity {
    String service;
    int price;

    public Amenity(String serviceParam, int priceParam) {
        this.service = serviceParam;
        this.price = priceParam;
    }

    public String getService() {
        return this.service;
    }

    public int getPrice() {
        return this.price;
    }
}
