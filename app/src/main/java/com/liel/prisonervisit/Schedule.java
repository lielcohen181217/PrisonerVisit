package com.liel.prisonervisit;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class Schedule implements Parcelable,Serializable {
   ArrayList<Meeting> sun;
    ArrayList<Meeting> mon;
    ArrayList<Meeting> tue;
    ArrayList<Meeting> wed;
    ArrayList<Meeting> thu;
    ArrayList<Meeting> fri;
    ArrayList<Meeting> sat;


    public Schedule() {
    }

    public Schedule(ArrayList<Meeting> sun, ArrayList<Meeting> mon, ArrayList<Meeting> tue, ArrayList<Meeting> wed, ArrayList<Meeting> thu, ArrayList<Meeting> fri, ArrayList<Meeting> sat) {
        this.sun = sun;
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
    }
    public Schedule(Schedule s){
        this.sun=s.sun;
        this.mon=s.mon;
        this.tue=s.tue;
        this.wed=s.wed;
        this.thu=s.thu;
        this.fri=s.fri;
        this.sat=s.sat;
    }

    public   ArrayList<Meeting> getSun() {
        return sun;
    }

    public void setSun(ArrayList<Meeting> sun) {
        this.sun = sun;
    }

    public ArrayList<Meeting> getMon() {
        return mon;
    }

    public void setMon(ArrayList<Meeting> mon) {
        this.mon = mon;
    }

    public ArrayList<Meeting> getTue() {
        return tue;
    }

    public void setTue(ArrayList<Meeting> tue) {
        this.tue = tue;
    }

    public ArrayList<Meeting> getWed() {
        return wed;
    }

    public void setWed(ArrayList<Meeting> wed) {
        this.wed = wed;
    }

    public ArrayList<Meeting> getThu() {
        return thu;
    }

    public void setThu(ArrayList<Meeting> thu) {
        this.thu = thu;
    }

    public ArrayList<Meeting> getFri() {
        return fri;
    }

    public void setFri(ArrayList<Meeting> fri) {
        this.fri = fri;
    }

    public ArrayList<Meeting> getSat() {
        return sat;
    }

    public void setSat(ArrayList<Meeting> sat) {
        this.sat = sat;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
           dest.writeSerializable(sun);
        dest.writeSerializable(mon);
        dest.writeSerializable(tue);
        dest.writeSerializable(wed);
        dest.writeSerializable(thu);
        dest.writeSerializable(fri);
        dest.writeSerializable(sat);

    }
    private Schedule(Parcel in) {
         sun = (ArrayList<Meeting>) in.readSerializable();
        mon = (ArrayList<Meeting>) in.readSerializable();
        tue = (ArrayList<Meeting>) in.readSerializable();
        wed = (ArrayList<Meeting>) in.readSerializable();
        thu = (ArrayList<Meeting>) in.readSerializable();
        fri = (ArrayList<Meeting>) in.readSerializable();
        sat = (ArrayList<Meeting>) in.readSerializable();
    }




    public static final Creator<Schedule> CREATOR = new Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
}

