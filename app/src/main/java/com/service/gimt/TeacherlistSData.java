package com.service.gimt;

public class TeacherlistSData {
    private String heading,temail,tphone;

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }


    public String getTemail() {
        return temail;
    }

    public void setTemail(String temail) {
        this.temail = temail;
    }

    public String getTphone() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

    public TeacherlistSData() {
    }

    public TeacherlistSData(String heading, String temail, String tphone) {
        this.heading = heading;
        this.temail = temail;
        this.tphone = tphone;


    }
}
