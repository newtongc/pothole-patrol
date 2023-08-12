package com.techelevator.model;

public class PotholeWithPhoneNumber {
    private Pothole pothole;
    private String phoneNumber;

    public PotholeWithPhoneNumber(Pothole pothole, String phoneNumber) {
        this.pothole = pothole;
        this.phoneNumber = phoneNumber;
    }
    public Pothole getPothole() {
        return pothole;
    }

    public void setPothole(Pothole pothole) {
        this.pothole = pothole;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
