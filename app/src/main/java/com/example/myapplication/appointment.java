package com.example.myapplication;

import java.sql.Time;
import java.util.Date;

public class appointment {

    //attributes
    private Date date;
    private Time time;
    private String summary;
    private Therapist therapist;

    //default constructor
    public appointment() {
    }

    //constructor
    public appointment(Date date, Time time, String summary, Therapist therapist) {
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

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }
}
