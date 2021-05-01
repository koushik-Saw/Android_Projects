package com.example.foodapp.model;

import com.google.firebase.database.Exclude;

public class Ordered_items {

    private String order_user;
    private String order_Rest_name;
    private String order_item_name;
    private String order_item_price;
    private String order_item_quantity;
    private String key;

    public Ordered_items(String order_user, String order_Rest_name, String order_item_name, String order_item_price, String order_item_quantity) {
        this.order_user = order_user;
        this.order_Rest_name = order_Rest_name;
        this.order_item_name = order_item_name;
        this.order_item_price = order_item_price;
        this.order_item_quantity = order_item_quantity;
    }


    public Ordered_items() {
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public String getOrder_user() {
        return order_user;
    }

    public void setOrder_user(String order_user) {
        this.order_user = order_user;
    }

    public String getOrder_Rest_name() {
        return order_Rest_name;
    }

    public void setOrder_Rest_name(String order_Rest_name) {
        this.order_Rest_name = order_Rest_name;
    }

    public String getOrder_item_name() {
        return order_item_name;
    }

    public void setOrder_item_name(String order_item_name) {
        this.order_item_name = order_item_name;
    }

    public String getOrder_item_price() {
        return order_item_price;
    }

    public void setOrder_item_price(String order_item_price) {
        this.order_item_price = order_item_price;
    }

    public String getOrder_item_quantity() {
        return order_item_quantity;
    }

    public void setOrder_item_quantity(String order_item_quantity) {
        this.order_item_quantity = order_item_quantity;
    }
}
