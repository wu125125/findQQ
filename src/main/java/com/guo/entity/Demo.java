package com.guo.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Demo implements Serializable {

    ArrayList<String> strs;

    String kill;

    public Demo() {
    }

    public Demo(ArrayList<String> strs) {
        this.strs = strs;
    }

    public String getKill() {
        return kill;
    }

    public void setKill(String kill) {
        this.kill = kill;
    }

    public ArrayList<String> getStrs() {
        return strs;
    }

    public void setStrs(ArrayList<String> strs) {
        this.strs = strs;
    }
}
