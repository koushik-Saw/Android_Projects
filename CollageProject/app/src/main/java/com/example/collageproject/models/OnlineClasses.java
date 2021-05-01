package com.example.collageproject.models;

public class OnlineClasses {

    String fac_sub;
    String sub;
    String faculty;
    String ClassLink;

    public OnlineClasses() {
    }

    public OnlineClasses(String fac_sub, String sub, String faculty, String classLink) {
        this.fac_sub = fac_sub;
        this.sub = sub;
        this.faculty = faculty;
        ClassLink = classLink;
    }

    public String getFac_sub() {
        return fac_sub;
    }

    public void setFac_sub(String fac_sub) {
        this.fac_sub = fac_sub;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getClassLink() {
        return ClassLink;
    }

    public void setClassLink(String classLink) {
        ClassLink = classLink;
    }
}
