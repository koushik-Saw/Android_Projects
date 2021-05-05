package com.example.collegeprojectadmin.models;

public class Courses {

    String course_name;
    String faculty;
    String year;
    String subjects;
    String fac_year;

    public String getFac_year() {
        return fac_year;
    }

    public void setFac_year(String fac_year) {
        this.fac_year = fac_year;
    }

    public Courses() {
    }

    public Courses(String course_name, String faculty, String year, String subjects, String fac_year) {
        this.course_name = course_name;
        this.faculty = faculty;
        this.year = year;
        this.subjects = subjects;
        this.fac_year = fac_year;
    }

    public Courses(String course_name) {
        this.course_name = course_name;
    }

    public Courses(String faculty, String year, String subjects, String fac_year) {
        this.faculty = faculty;
        this.year = year;
        this.subjects = subjects;
        this.fac_year = fac_year;
    }

    public Courses(String faculty, String year) {
        this.faculty = faculty;
        this.year = year;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}
