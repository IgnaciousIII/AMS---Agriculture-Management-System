package com.agriculture.app;

public class Subsidy {
    private String subsidyName;
    private String description;
    private String eligibilityCriteria;
    private String region;

    public Subsidy(String subsidyName, String description, String eligibilityCriteria, String region) {
        this.subsidyName = subsidyName;
        this.description = description;
        this.eligibilityCriteria = eligibilityCriteria;
        this.region = region;
    }

    public String getSubsidyName() {
        return subsidyName;
    }

    public String getDescription() {
        return description;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public String getRegion() {
        return region;
    }
}
