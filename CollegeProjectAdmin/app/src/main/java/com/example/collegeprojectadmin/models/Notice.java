package com.example.collegeprojectadmin.models;

public class Notice {

    private String Target_class;
    private String Date;
    private String Notice_title;
    private String Notice_body;

    public Notice() {

    }

    public Notice(String target_class, String date, String notice_title, String notice_body) {
        Target_class = target_class;
        Date = date;
        Notice_title = notice_title;
        Notice_body = notice_body;
    }

    public String getTarget_class() {
        return Target_class;
    }

    public void setTarget_class(String target_class) {
        Target_class = target_class;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNotice_title() {
        return Notice_title;
    }

    public void setNotice_title(String notice_title) {
        Notice_title = notice_title;
    }

    public String getNotice_body() {
        return Notice_body;
    }

    public void setNotice_body(String notice_body) {
        Notice_body = notice_body;
    }
}
