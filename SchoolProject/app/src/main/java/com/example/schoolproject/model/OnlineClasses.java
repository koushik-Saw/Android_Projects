package com.example.schoolproject.model;

public class OnlineClasses {
    String online_class_name_sec_sub;
    String online_class_link;
    String online_class_sub;

    public OnlineClasses(String online_class_name_sec_sub, String online_class_link, String online_class_sub) {
        this.online_class_name_sec_sub = online_class_name_sec_sub;
        this.online_class_link = online_class_link;
        this.online_class_sub = online_class_sub;
    }

    public OnlineClasses() {
    }

    public String getOnline_class_sub() {
        return online_class_sub;
    }

    public void setOnline_class_sub(String online_class_sub) {
        this.online_class_sub = online_class_sub;
    }

    public String getOnline_class_name_sec_sub() {
        return online_class_name_sec_sub;
    }

    public void setOnline_class_name_sec_sub(String online_class_name_sec_sub) {
        this.online_class_name_sec_sub = online_class_name_sec_sub;
    }

    public String getOnline_class_link() {
        return online_class_link;
    }

    public void setOnline_class_link(String online_class_link) {
        this.online_class_link = online_class_link;
    }
}
