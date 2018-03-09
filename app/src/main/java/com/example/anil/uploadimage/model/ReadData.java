package com.example.anil.uploadimage.model;

/**
 * Created by Anil on 3/8/2018.
 */

public class ReadData {
    int id;
    String name,path;

    public ReadData(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public ReadData() {

    }

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
