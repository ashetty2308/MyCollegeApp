package com.example.mycollegeapp;

public class AssignmentsItem {
    String className;
    String classTime;
    String classInstructor;

    public AssignmentsItem(String className, String classTime, String classInstructor) {
        this.className = className;
        this.classTime = classTime;
        this.classInstructor = classInstructor;
    }
    public String getClassName() {
        return className;
    }
    public String getClassTime() {
        return classTime;
    }
    public String getClassInstructor() {
        return classInstructor;
    }
}
