package com.service.gimt;

public class ExamScheduleData {
   String name,date,room,stime,etime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public ExamScheduleData(String name, String date, String room, String stime, String etime) {
        this.name = name;
        this.date=date;
        this.room=room;
        this.stime=stime;
        this.etime=etime;
    }

    public ExamScheduleData() {
    }
}
