package com.example.foodapp.model;

import com.google.firebase.database.Exclude;

public class Favorites {

    private long id;
    private String name;
    private String url;
    private String key;
    private String email;

    public Favorites() {
    }

    public Favorites(long id, String name, String url, String email) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.email = email;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
