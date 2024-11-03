package com.agriculture.app;

public class Scheme {
    private String schemeName;
    private String description;
    private String eligibilityCriteria;
    private String region;
    private String cropType;

    public Scheme(String schemeName, String description, String eligibilityCriteria, String region, String cropType) {
        this.schemeName = schemeName;
        this.description = description;
        this.eligibilityCriteria = eligibilityCriteria;
        this.region = region;
        this.cropType = cropType;
    }

    // Getters and Setters
    public String getSchemeName() { return schemeName; }
    public String getDescription() { return description; }
    public String getEligibilityCriteria() { return eligibilityCriteria; }
    public String getRegion() { return region; }
    public String getCropType() { return cropType; }
}
