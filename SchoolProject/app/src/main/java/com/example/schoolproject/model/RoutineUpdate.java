package com.example.schoolproject.model;

public class RoutineUpdate {

    private String routineClassName;
    private String routineSecName;
    private String routineImageUri;

    public RoutineUpdate() {
    }

    public RoutineUpdate(String routineClassName, String routineSecName, String routineImageUri) {
        this.routineClassName = routineClassName;
        this.routineSecName = routineSecName;
        this.routineImageUri = routineImageUri;
    }

    public String getRoutineClassName() {
        return routineClassName;
    }

    public void setRoutineClassName(String routineClassName) {
        this.routineClassName = routineClassName;
    }

    public String getRoutineSecName() {
        return routineSecName;
    }

    public void setRoutineSecName(String routineSecName) {
        this.routineSecName = routineSecName;
    }

    public String getRoutineImageUri() {
        return routineImageUri;
    }

    public void setRoutineImageUri(String routineImageUri) {
        this.routineImageUri = routineImageUri;
    }
}
