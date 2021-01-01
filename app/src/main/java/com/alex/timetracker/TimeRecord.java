package com.alex.timetracker;

public class TimeRecord {
    private String time;
    private String note;

    public TimeRecord(String time, String note){
        this.time = time;
        this.note = note;
    }

    public String getTime() { return time; }
    public String getNote() { return note; }
    public void setTime(String time) { this.time = time; }
    public void setNote(String note) { this.note = note; }
}
