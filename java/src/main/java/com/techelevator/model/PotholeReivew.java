package com.techelevator.model;

import java.util.Date;

public class PotholeReivew {
    private boolean inspected;
    private boolean repaired;
    private Date inspectedDate;
    private Date repairDate;
    private int severity;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
}

