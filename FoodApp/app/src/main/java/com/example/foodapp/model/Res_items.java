package com.example.foodapp.model;

public class Res_items {


    private String item_name;
    private String item_price;
    private String restaurant_name;
    private String item_images;

    public Res_items() {
    }

    public Res_items(String item_name, String item_price, String restaurant_name, String item_images) {
        this.item_name = item_name;
        this.item_price = item_price;
        this.restaurant_name = restaurant_name;
        this.item_images = item_images;
    }

    public String getItem_images() {
        return item_images;
    }

    public void setItem_images(String item_images) {
        this.item_images = item_images;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

}
