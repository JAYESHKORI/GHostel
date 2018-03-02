package com.example.jayesh.ghostel.Model;

/**
 * Created by jayesh on 2/3/18.
 */

public class CommonData
{
    private int id;
    private String fname;
    private String lname;
    private int hid;
    private int bid;
    private String hname;
    private String bname;

    public CommonData(int id, String fname, String lname, int hid, int bid, String hname, String bname) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.hid = hid;
        this.bid = bid;
        this.hname = hname;
        this.bname = bname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
}
