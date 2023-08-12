package com.techelevator.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

public class Pothole {
    private int id;
    private String description;

    private int severity;
    private String address;
    private Date reportedDate;
    private Date inspectedDate;
    private Date repairDate;
    private boolean canContact;
    private boolean inspected;
    private boolean repaired;
    private String zipcode;
    private boolean inTraffic;
    private boolean potentialDamage;
    private String locationDetails;
    private int reporterId;
    private Reporter reporter;

    public Pothole(int id, String description, int severity, String address, Date reportedDate, Date inspectedDate,
                   Date repairDate, boolean canContact, boolean inspected, boolean repaired, String zipcode,
                   boolean inTraffic, boolean potentialDamage, String locationDetails, int reporterId, Reporter reporter) {
        this.id = id;
        this.description = description;
        this.severity = severity;
        this.address = address;
        this.reportedDate = reportedDate;
        this.inspectedDate = inspectedDate;
        this.repairDate = repairDate;
        this.canContact = canContact;
        this.inspected = inspected;
        this.repaired = repaired;
        this.zipcode = zipcode;
        this.inTraffic = inTraffic;
        this.potentialDamage = potentialDamage;
        this.locationDetails = locationDetails;
        this.reporterId = reporterId;
        this.reporter = reporter;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public Date getInspectedDate() {
        return inspectedDate;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public boolean isCanContact() {
        return canContact;
    }

    public boolean isInspected() {
        return inspected;
    }

    public boolean isRepaired() {
        return repaired;
    }

    public String getZipcode() {
        return zipcode;
    }

    public boolean isInTraffic() {
        return inTraffic;
    }

    public boolean isPotentialDamage() {
        return potentialDamage;
    }

    public void setInspectedDate(Date inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public void setCanContact(boolean canContact) {
        this.canContact = canContact;
    }

    public void setInspected(boolean inspected) {
        this.inspected = inspected;
    }

    public void setRepaired(boolean repaired) {
        this.repaired = repaired;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setInTraffic(boolean inTraffic) {
        this.inTraffic = inTraffic;
    }

    public void setPotentialDamage(boolean potentialDamage) {
        this.potentialDamage = potentialDamage;
    }

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(String locationDetails) {
        this.locationDetails = locationDetails;
    }

    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }
}
