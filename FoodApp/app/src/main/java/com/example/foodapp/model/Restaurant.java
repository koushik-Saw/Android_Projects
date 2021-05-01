package com.example.foodapp.model;

public class Restaurant {

    private String url;
    private String name;
    private String rating;

    public Restaurant() {
    }

    public Restaurant(String url, String name, String rating) {
        this.url = url;
        this.name = name;
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
