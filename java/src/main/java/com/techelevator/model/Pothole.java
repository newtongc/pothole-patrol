package com.techelevator.model;


import java.util.Date;

public class Pothole {
    private int id;
    private String description;

    private int severity;
    private String location;
    private Date reportedDate;

    public Pothole(int id, String description, int severity, String location, Date reportedDate) {
        this.id = id;
        this.description = description;
        this.severity = severity;
        this.location = location;
        this.reportedDate = reportedDate;
    }

    public Pothole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }
}
