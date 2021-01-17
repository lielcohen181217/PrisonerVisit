package com.liel.prisonervisit;

import android.widget.ArrayAdapter;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.io.Serializable;
@IgnoreExtraProperties

public class prison implements Serializable {
    private String key ;
    private String name;
    private String address;
    private ArrayList<department> departments;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public prison(String key,String name, String address) {
        this.key="";
        this.name = name;
        this.address = address;
        this.departments = new ArrayList<department>();
        departments.add(new department());
    }

    public prison() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

   public void setDepartments(ArrayList<department> departments) {
        this.departments = departments;
    }

    public String getName() {

        return name;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<department> getDepartments() {
        return departments;
    }

    public void addDepartment(department d){
       this.departments.add(d);
    }


}
