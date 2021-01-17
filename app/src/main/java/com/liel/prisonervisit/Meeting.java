package com.liel.prisonervisit;

import java.io.Serializable;

public class Meeting extends Time implements Serializable {
String day;
  boolean  occupied;
  int IDofVisitor;
  int IDofprisoner;


  public Meeting(){





  }



    public Meeting(String day,int startHour, int finisgHour, boolean occupied, int vistorId, int prisonerId) {
       super( startHour,  finisgHour);
       this.day=day;
        this.occupied = occupied;
        IDofVisitor = vistorId;
        IDofprisoner = prisonerId;
    }
    public Meeting(Meeting m){
      super(m.startHour,m.finisgHour);
      this.day=m.day;
      this.occupied=m.occupied;
      this.IDofVisitor=m.IDofVisitor;
      this.IDofprisoner=m.IDofprisoner;


    }




    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getVistorId() {
        return IDofVisitor;
    }

    public void setVistorId(int vistorId) {
        IDofVisitor = vistorId;
    }

    public int getPrisonerId() {
        return IDofprisoner;
    }

    public void setPrisonerId(int prisonerId) {
        IDofprisoner = prisonerId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
