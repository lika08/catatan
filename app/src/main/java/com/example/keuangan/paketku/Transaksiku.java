package com.example.keuangan.paketku;

public class Transaksiku {
    String kunci;
    String isi;

    public Transaksiku(){

    }

    public Transaksiku(String kunci, String isi) {
        this.kunci = kunci;
        this.isi = isi;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getKunci() {
        return kunci;
    }

    public void setKunci(String kunci) {
        this.kunci = kunci;
    }
}
