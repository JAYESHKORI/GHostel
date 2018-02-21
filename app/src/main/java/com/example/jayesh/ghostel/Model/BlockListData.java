package com.example.jayesh.ghostel.Model;

/**
 * Created by jayesh on 19/2/18.
 */

public class BlockListData
{
    private int blockid;
    private String bname;
    private int hostelid;
    private String hName;
    private String type;
    private int capacity;

    public BlockListData(int blockid, String bname, int hostelid, String hName, String type, int capacity) {
        this.blockid = blockid;
        this.bname = bname;
        this.hostelid = hostelid;
        this.hName = hName;
        this.type = type;
        this.capacity = capacity;
    }

    public int getBlockid() {
        return blockid;
    }

    public String getBname() {
        return bname;
    }

    public int getHostelid() {
        return hostelid;
    }

    public String gethName() {
        return hName;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }
}
