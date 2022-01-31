package com.example.myapplication;

import java.sql.Time;
import java.util.Date;

public class Reminder {

    //attributes
    private String date;
    private String  time;
    private String note;

    //default constructor
    public Reminder() {}

    //constructor
    public Reminder(String date, String time, String note) {
        this.date = date;
        this.time = time;
        this.note = note;
    }

    //getters & setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Reminder:" + this.note + this.date + this.time;
    }
}
