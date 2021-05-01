package com.example.schoolproject.model;

public class Sections {

    String Classnames;
    String Classlink;
    String Section;

    public Sections() {
    }

    public Sections(String classnames, String classlink, String section) {
        Classnames = classnames;
        Classlink = classlink;
        Section = section;
    }

    public Sections(String classnames, String section) {
        Classnames = classnames;
        Section = section;
    }

    public String getClassnames() {
        return Classnames;
    }

    public void setClassnames(String classnames) {
        Classnames = classnames;
    }

    public String getClasslink() {
        return Classlink;
    }

    public void setClasslink(String classlink) {
        Classlink = classlink;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }
}
