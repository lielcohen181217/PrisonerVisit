package com.liel.prisonervisit;

import java.io.Serializable;

public class Time implements Serializable {
    String day;
    int startHour;
    int finisgHour;

    public Time() {

    }
    public Time(int startHour, int finisgHour,String day){
        this.startHour = startHour;
        this.finisgHour = finisgHour;
        this.day=day;
    }

    public Time(int startHour, int finisgHour) {
        this.startHour = startHour;
        this.finisgHour = finisgHour;
    }
    public Time(Time t){
        this.startHour=t.startHour;
        this.finisgHour=t.finisgHour;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getFinisgHour() {
        return finisgHour;
    }

    public void setFinisgHour(int finisgHour) {
        this.finisgHour = finisgHour;
    }
}