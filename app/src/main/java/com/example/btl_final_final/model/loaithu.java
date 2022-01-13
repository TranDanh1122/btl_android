package com.example.btl_final_final.model;

public class loaithu {
    private String loaithu,id;

    public loaithu(String loaithu,String id) {
        this.loaithu = loaithu;
        this.id = id;
    }
    public loaithu(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLoaithu(String loaithu) {
        this.loaithu = loaithu;
    }

    public String getLoaithu() {
        return loaithu;
    }
}
