package com.liel.prisonervisit;

import java.util.ArrayList;

public class Warden extends Employe {
    String key;
    String prisonname;
    String department;
    ArrayList<Time> schedule=new ArrayList<>();


    public Warden() {

    }
    public Warden(String key,String username, String password, String rank,String prisonname,String department, ArrayList<Time> schedule) {
        super(username, password, rank);
        this.prisonname=prisonname;
        this.department=department;
        this.schedule = schedule;
        this.key=key;
    }



    public Warden(String username, String password, String rank,String prisonname,String department, ArrayList<Time> schedule) {
        super(username, password, rank);
        this.prisonname=prisonname;
        this.department=department;
        this.schedule = schedule;
    }

    public ArrayList<Time> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Time> schedule) {
        this.schedule = schedule;
    }

    public String getPrisonname() {
        return prisonname;
    }

    public void setPrisonname(String prisonname) {
        this.prisonname = prisonname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
