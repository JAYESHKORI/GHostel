package com.example.jayesh.ghostel.Model;

/**
 * Created by jayesh on 2/3/18.
 */

public class RectorListData
{
    private int id;
    private String fname;
    private String lname;
    private String mname;
    private String dob;
    private String email;
    private long contact;
    private String address;
    private long em_contact;
    private int hostelid;
    private int blockid;
    private String username;
    private String password;
    private String dp_path;

    public RectorListData(int id, String fname, String lname, String mname, String dob, String email, long contact, String address, long em_contact, int hostelid, int blockid, String username, String password, String dp_path) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.mname = mname;
        this.dob = dob;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.em_contact = em_contact;
        this.hostelid = hostelid;
        this.blockid = blockid;
        this.username = username;
        this.password = password;
        this.dp_path = dp_path;
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

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getEm_contact() {
        return em_contact;
    }

    public void setEm_contact(long em_contact) {
        this.em_contact = em_contact;
    }

    public int getHostelid() {
        return hostelid;
    }

    public void setHostelid(int hostelid) {
        this.hostelid = hostelid;
    }

    public int getBlockid() {
        return blockid;
    }

    public void setBlockid(int blockid) {
        this.blockid = blockid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDp_path() {
        return dp_path;
    }

    public void setDp_path(String dp_path) {
        this.dp_path = dp_path;
    }
}
