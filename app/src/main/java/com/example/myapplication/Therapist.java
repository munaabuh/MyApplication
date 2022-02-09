package com.example.myapplication;

import java.sql.Time;
import java.time.DayOfWeek;

public class Therapist {

    //attributes
    private String phone;
    private String name;
    private String area;
    private String major;
    private String gender;

    //default constructor
    public Therapist() {}

    //constructor
    public Therapist(String  phone, String name, String area, String major, String gender) {
        this.phone = phone;
        this.name = name;
        this.area = area;
        this.major = major;
        this.gender = gender;
    }

    //getters & setters
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
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


    @Override
    public String toString() {
        return

                this.name + '\n' +
                this.area + '\n' +
                this.major + '\n'+
                this.gender + '\n'+
                this.phone;
    }
}
