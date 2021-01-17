package com.liel.prisonervisit;

public class Employe {
String key;
    String username;
    String password;
    String rank;
    public Employe(){

    }
    public Employe(String key,String username, String password,String rank) {
    this.key=key;
        this.username = username;
        this.password=password;
        this.rank=rank;
    }


    public Employe(String username, String password,String rank) {

        this.username = username;
        this.password=password;
        this.rank=rank;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
