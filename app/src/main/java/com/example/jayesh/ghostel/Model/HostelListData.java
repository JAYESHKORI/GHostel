package com.example.jayesh.ghostel.Model;

/**
 * Created by jayesh on 13/2/18.
 */

public class HostelListData
{
    private int id;
    private String name;
    private String description;
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public HostelListData(int id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
