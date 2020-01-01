package com.license.entity;

public class LicenseEntity {
    private int id;
    private String company;
    private String installedID;
    private int licenseDays;
    private int clusterSize;
    private String licenseKey;
    private long activeTime;
    private int isTemp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInstalledID() {
        return installedID;
    }

    public void setInstalledID(String installedID) {
        this.installedID = installedID;
    }

    public int getLicenseDays() {
        return licenseDays;
    }

    public void setLicenseDays(int licenseDays) {
        this.licenseDays = licenseDays;
    }

    public int getClusterSize() {
        return clusterSize;
    }

    public void setClusterSize(int clusterSize) {
        this.clusterSize = clusterSize;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public int getIsTemp() {
        return isTemp;
    }

    public void setIsTemp(int isTemp) {
        this.isTemp = isTemp;
    }
}
