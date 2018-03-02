package com.example.jayesh.ghostel.Model;

/**
 * Created by jayesh on 2/3/18.
 */

public class StudentListData
{
    private int id;
    private long enroll_no;
    private String fname;
    private String lname;
    private String mname;
    private String dob;
    private String email;
    private long contact;
    private String address;
    private long p_contact;
    private long em_contact;
    private String college;
    private int hostelid;
    private int blockid;
    private String username;
    private String password;
    private String dp_path;

    public StudentListData(int id, long enroll_no, String fname, String lname, String mname, String dob, String email, long contact, String address, long p_contact, long em_contact, String college, int hostelid, int blockid, String username, String password, String dp_path) {
        this.id = id;
        this.enroll_no = enroll_no;
        this.fname = fname;
        this.lname = lname;
        this.mname = mname;
        this.dob = dob;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.p_contact = p_contact;
        this.em_contact = em_contact;
        this.college = college;
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

    public long getEnroll_no() {
        return enroll_no;
    }

    public void setEnroll_no(long enroll_no) {
        this.enroll_no = enroll_no;
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

    public long getP_contact() {
        return p_contact;
    }

    public void setP_contact(long p_contact) {
        this.p_contact = p_contact;
    }

    public long getEm_contact() {
        return em_contact;
    }

    public void setEm_contact(long em_contact) {
        this.em_contact = em_contact;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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
