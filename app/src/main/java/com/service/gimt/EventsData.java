package com.service.gimt;

public class EventsData {
    private String heading;
    private String details;
    private String date;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public EventsData() {
    }



    public EventsData(String heading, String details, String date) {
        this.heading = heading;
        this.details = details;
        this.date = date;
    }
}
