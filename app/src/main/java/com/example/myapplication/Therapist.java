package com.example.myapplication;

import java.sql.Time;
import java.time.DayOfWeek;

public class Therapist {

    //attributes
    private int phone;
    private String name;
    private String area;
    private String major;
    private String gender;
    private String workingHours;
    private String workingDays;

    //default constructor
    public Therapist() {}

    //constructor
    public Therapist(int phone, String name, String area, String major, String gender, String workingHours, String workingDays) {
        this.phone = phone;
        this.name = name;
        this.area = area;
        this.major = major;
        this.gender = gender;
        this.workingHours = workingHours;
        this.workingDays = workingDays;
    }

    //getters & setters
    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String workingDays) {
        this.workingDays = workingDays;
    }
}
