package com.agriculture.app;

public class Farmer {
    private String name;
    private String email;
    private long aadhar;
    private double landSize;
    private String cropType;

    public Farmer(String name, String email, long aadhar, double landSize, String cropType) {
        this.name = name;
        this.email = email;
        this.aadhar = aadhar;
        this.landSize = landSize;
        this.cropType = cropType;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getAadhar() {
        return aadhar;
    }

    public double getLandSize() {
        return landSize;
    }

    public String getCropType() {
        return cropType;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAadhar(long aadhar) {
        this.aadhar = aadhar;
    }

    public void setLandSize(double landSize) {
        this.landSize = landSize;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }
}
