package com.liel.prisonervisit;

import java.util.ArrayList;
import java.util.List;

public class Prisoner {
      String key;
      String namePrison;
       String department;
      int cellnum;
      ArrayList<Integer> visitorsId;
       String prisonerId;
       String status;
   Schedule meetings;

  public Prisoner(){

  }

    public Prisoner(String key,String namePrison, String department, int cell_num, ArrayList<Integer> visitorsId, String prisonerId, String status,   Schedule meetings) {
       this.namePrison = namePrison;
        this.department = department;
        this.cellnum = cell_num;
       this.visitorsId = visitorsId;
        this.prisonerId = prisonerId;
        this.status = status;
        this.meetings = meetings;
        this.key=key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNamePrison() {
        return namePrison;
    }

    public void setNamePrison(String namePrison) {
        this.namePrison = namePrison;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCellnum() {
        return cellnum;
    }

    public void setCellnum(int cellnum) {
        this.cellnum = cellnum;
    }

    public ArrayList<Integer> getVisitorsId() {
        return visitorsId;
    }

    public void setVisitorsId(ArrayList<Integer> visitorsId) {
        this.visitorsId = visitorsId;
    }

    public String getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(String prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Schedule getMeetings() {
        return meetings;
    }

    public void setMeetings(Schedule meetings) {
        this.meetings = meetings;
    }
}
