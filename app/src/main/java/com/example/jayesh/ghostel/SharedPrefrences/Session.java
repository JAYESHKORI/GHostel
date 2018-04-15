package com.example.jayesh.ghostel.SharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context context) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setid(int id) {
        prefs.edit().putInt("id", id).commit();
    }

    public int getid() {
        int id = prefs.getInt("id",0);
        return id;
    }

    public void setUsername(String username) {
        prefs.edit().putString("username", username).commit();
    }

    public String getUsername() {
        String username = prefs.getString("username","");
        return username;
    }

    public void setUsertype(String usertype) {
        prefs.edit().putString("usertype", usertype).commit();
    }

    public String getUsertype() {
        String usertype = prefs.getString("usertype","");
        return usertype;
    }

    public void setImgURL(String imgURL){
        prefs.edit().putString("imgURL", imgURL).commit();
    }

    public String getImgURL(){
        String imgURL = prefs.getString("imgURL","");
        return imgURL;
    }

    public void sethostelid(int hostelid) {
        prefs.edit().putInt("hostelid", hostelid).commit();
    }

    public int gethostelid() {
        int hostelid = prefs.getInt("hostelid",-1);
        return hostelid;
    }

    public void setblockid(int blockid) {
        prefs.edit().putInt("blockid", blockid).commit();
    }

    public int getblockid() {
        int blockid = prefs.getInt("blockid",-1);
        return blockid;
    }
}