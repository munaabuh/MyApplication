package com.example.myapplication;

public class Item {

    private int amount;
    private boolean isHappy;
    private String description;
    private int resid; //image id to be loaded

    public Item(int amount, boolean isHappy, String description, int resid) {

        this.resid = resid;
        this.amount = amount;
        this.isHappy = isHappy;
        this.description = description;

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isHappy() {
        return isHappy;
    }

    public void setHappy(boolean happy) {
        isHappy = happy;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    @Override
    public String toString() {
        return "Item{" +
                "amount=" + amount +
                ", isHappy=" + isHappy +
                ", description='" + description + '\'' +
                ", resid=" + resid +
                '}';
    }
}
