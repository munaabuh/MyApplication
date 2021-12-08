package com.example.myapplication;

import java.sql.Time;
import java.util.Date;

public class Reminder {

    //attributes
    private Date date;
    private Time time;
    private String description;

    //default constructor
    public Reminder() {}

    //constructor
    public Reminder(Date date, Time time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    //getters & setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
