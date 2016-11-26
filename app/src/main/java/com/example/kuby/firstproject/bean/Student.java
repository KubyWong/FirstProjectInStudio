package com.example.kuby.firstproject.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/22.
 */
public class Student {
    private String name;
    private List<Course> courses;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
