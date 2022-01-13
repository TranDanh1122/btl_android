package com.example.btl_final_final.model;

public class khoanthu {
    private String khoanthu,id,loaithu;

    public khoanthu(String khoanthu,String id,String loaithu) {
        this.khoanthu = khoanthu;
        this.id=id;
        this.loaithu=loaithu;
    }

    public void setLoaithu(String loaithu) {
        this.loaithu = loaithu;
    }

    public String getLoaithu() {
        return loaithu;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setKhoanthu(String khoanthu) {
        this.khoanthu = khoanthu;
    }

    public String getKhoanthu() {
        return khoanthu;
    }
}
