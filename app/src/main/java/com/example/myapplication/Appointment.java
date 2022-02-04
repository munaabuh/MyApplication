package com.example.myapplication;

import java.sql.Time;
import java.util.Date;

public class Appointment {

    //attributes
    private String date;
    private String time;
    private String summary;
    private String therapist;

    //default constructor
    public Appointment() {
    }

    //constructor
    public Appointment(String date, String time, String summary, String therapist) {
        this.date = date;
        this.time = time;
        this.summary = summary;
        this.therapist = therapist;
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getTherapist() {
        return therapist;
    }

    public void setTherapist(String therapist) {
        this.therapist = therapist;
    }
}
