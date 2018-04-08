package com.example.jayesh.ghostel.Model;

/**
 * Created by jayesh on 19/2/18.
 */

public class RoomListData
{
    private int roomid;
    private String roomno;
    private int hostelid;
    private String hostelname;
    private int blockid;
    private String blockname;
    private int capacity;
    private int current_no;

    public RoomListData(int roomid, String roomno, int hostelid, String hostelname, int blockid, String blockname, int capacity, int current_no) {
        this.roomid = roomid;
        this.roomno = roomno;
        this.hostelid = hostelid;
        this.hostelname = hostelname;
        this.blockid = blockid;
        this.blockname = blockname;
        this.capacity = capacity;
        this.current_no = current_no;
    }

    public int getRoomid() {
        return roomid;
    }

    public String getRoomno() {
        return roomno;
    }

    public int getHostelid() {
        return hostelid;
    }

    public int getBlockid() {
        return blockid;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrent_no() {
        return current_no;
    }

    public String getHostelname() {
        return hostelname;
    }

    public String getBlockname() {
        return blockname;
    }
}
