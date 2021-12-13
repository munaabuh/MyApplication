package com.example.myapplication;

import java.sql.Time;
import java.util.Date;

public class Reminder {

    //attributes
    private Date date;
    private Time time;
    private Appointment appointment;

    //default constructor
    public Reminder() {}

    //constructor
    public Reminder(Date date, Time time, Appointment appointment) {
        this.date = date;
        this.time = time;
        this.appointment = appointment;
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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
