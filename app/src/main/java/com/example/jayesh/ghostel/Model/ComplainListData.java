package com.example.jayesh.ghostel.Model;

/**
 * Created by jayesh on 8/4/18.
 */

public class ComplainListData
{
    private int complainid;
    private String title;
    private String detail;
    private int hostelid;
    private String hostelname;
    private int blockid;
    private String blockname;
    private int roomid;
    private String roomnono;
    private int studentid;
    private String studentname;
    private int status;

    public int getComplainid() {
        return complainid;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public int getHostelid() {
        return hostelid;
    }

    public String getHostelname() {
        return hostelname;
    }

    public int getBlockid() {
        return blockid;
    }

    public String getBlockname() {
        return blockname;
    }

    public int getRoomid() {
        return roomid;
    }

    public String getRoomnono() {
        return roomnono;
    }

    public int getStudentid() {
        return studentid;
    }

    public String getStudentname() {
        return studentname;
    }

    public int getStatus() {
        return status;
    }

    public ComplainListData(int complainid, String title, String detail, int hostelid, String hostelname, int blockid, String blockname, int roomid, String roomnono, int studentid, String studentname, int status) {
        this.complainid = complainid;
        this.title = title;
        this.detail = detail;
        this.hostelid = hostelid;
        this.hostelname = hostelname;
        this.blockid = blockid;
        this.blockname = blockname;
        this.roomid = roomid;
        this.roomnono = roomnono;
        this.studentid = studentid;
        this.studentname = studentname;
        this.status = status;
    }
}
