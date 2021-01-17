package com.liel.prisonervisit;

import java.io.Serializable;
import java.util.ArrayList;

public class ListVisitorId implements Serializable {

    ArrayList<Integer> listV;


    public ListVisitorId() {
    }

    public ListVisitorId(ArrayList<Integer> listV) {
        this.listV = listV;
    }

    public ArrayList<Integer> getListV() {
        return listV;
    }

    public void setListV(ArrayList<Integer> listV) {
        this.listV = listV;
    }
}
