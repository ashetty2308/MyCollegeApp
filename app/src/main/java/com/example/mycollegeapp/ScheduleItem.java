package com.example.mycollegeapp;

import android.widget.TextView;

public class ScheduleItem {
    String className;
    String classTime;
    String classInstructor;

    public ScheduleItem (String className, String classTime, String classInstructor) {
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
