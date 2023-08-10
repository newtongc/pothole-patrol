package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class RegisterPotholeDto {
    @NotEmpty
    private String description;
    @NotEmpty
    private String address;
   
    private int severity;

   private Date reportedDate;

    private Date inspectedDate;
    private Date repairDate;
    @JsonProperty("can_contact")
    private boolean canContact;
    private boolean inspected;
    private boolean repaired;
    private String zipcode;
    @JsonProperty("in_traffic")
    private boolean inTraffic;
    @JsonProperty("potential_damage")
    private boolean potentialDamage;
    @JsonProperty("location_details")
    private String locationDetails;
    private int reporterId;


    public int getReporterId() {
        return reporterId;
    }

    public void setReporterId(int reporterId) {
        this.reporterId = reporterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
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

    public void setInspectedDate(Date inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public boolean isCanContact() {
        return canContact;
    }

    public void setCanContact(boolean canContact) {
        this.canContact = canContact;
    }

    public boolean isInspected() {
        return inspected;
    }

    public void setInspected(boolean inspected) {
        this.inspected = inspected;
    }

    public boolean isRepaired() {
        return repaired;
    }

    public void setRepaired(boolean repaired) {
        this.repaired = repaired;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public boolean isInTraffic() {
        return inTraffic;
    }

    public void setInTraffic(boolean inTraffic) {
        this.inTraffic = inTraffic;
    }

    public boolean isPotentialDamage() {
        return potentialDamage;
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
}
