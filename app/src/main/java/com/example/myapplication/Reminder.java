package com.example.myapplication;

import java.sql.Time;
import java.util.Date;

public class Reminder {

    //attributes
    private String date;
    private String time;
    private String notes;

    //default constructor
    public Reminder() {}

    //constructor
    public Reminder(String date, String time, String notes) {
        this.date = date;
        this.time = time;
        this.notes = notes;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString(){
        return "Reminder:" + " " + this.date
                + " " + this.time + " " + this.notes;
    }
}
