package com.example.myapplication;

public class HourRange {
    //attributes
    private int from;
    private int to;

    //default constructor
    public HourRange() {
    }

    //constructor
    public HourRange(int from, int to) {
        this.from = from;
        this.to = to;
    }

    //getters & setters
    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
