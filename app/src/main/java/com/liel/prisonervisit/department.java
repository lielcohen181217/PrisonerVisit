package com.liel.prisonervisit;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class department implements Serializable {
    private String name;
    private String type;
    private String numOfCells;

    public department()  {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumOfCells(String numOfCells) {

        this.numOfCells = numOfCells;
    }

    public String getName() {
        return name;

    }

    public String getType() {
        return type;
    }

    public String getNumOfCells() {
        return numOfCells;
    }

    public department(String name, String type, String numOfCells) {

        this.name = name;
        this.type = type;
        this.numOfCells = numOfCells;
    }
}