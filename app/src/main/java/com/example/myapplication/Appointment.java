package com.example.myapplication;

import java.sql.Time;
import java.util.Date;

public class Appointment {

    //attributes
    private Date date;
    private Time time;
    private String summary;
    private String therapist;

    //default constructor
    public Appointment() {
    }

    //constructor
    public Appointment(Date date, Time time, String summary, String therapist) {
        this.date = date;
        this.time = time;
        this.summary = summary;
        this.therapist = therapist;
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
