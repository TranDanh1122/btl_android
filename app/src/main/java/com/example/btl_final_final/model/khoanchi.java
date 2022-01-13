package com.example.btl_final_final.model;

public class khoanchi {
    private String khoanchi,id,loaichi;

    public khoanchi(String khoanchi,String id,String loaichi) {
        this.khoanchi = khoanchi;
        this.id=id;
        this.loaichi=loaichi;
    }

    public void setLoaichi(String loaichi) {
        this.loaichi = loaichi;
    }

    public String getLoaichi() {
        return loaichi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setKhoanchi(String khoanchi) {
        this.khoanchi = khoanchi;
    }

    public String getKhoanchi() {
        return khoanchi;
    }
}
