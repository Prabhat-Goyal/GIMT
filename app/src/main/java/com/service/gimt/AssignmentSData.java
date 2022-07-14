package com.service.gimt;

public class AssignmentSData {
    private String heading,pdf,sdate;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public AssignmentSData() {
    }

    public AssignmentSData(String heading, String sdate) {
        this.heading = heading;
        this.sdate=sdate;

    }
}
