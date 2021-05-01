package com.example.schoolproject.model;

public class AddSections {
    String Classnames;
    String Section;

    public AddSections(String classnames, String section) {
        Classnames = classnames;
        Section = section;
    }

    public AddSections() {
    }

    public String getClassnames() {
        return Classnames;
    }

    public void setClassnames(String classnames) {
        Classnames = classnames;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }
}
