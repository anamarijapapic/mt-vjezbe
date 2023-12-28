package org.oss.lab4;

import com.google.firebase.database.PropertyName;

public class Subject {
    public String uid;

    @PropertyName("ime")
    public String name;
    @PropertyName("predavac")
    public String lecturer;
    @PropertyName("godina")
    public Long year;

    public Subject() {}

    public Subject(String name, String lecturer, Long year) {
        this.name = name;
        this.lecturer = lecturer;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public Long getYear() {
        return year;
    }
}
