package com.nore.kalkulator_gampang.Model;

public class Barang {
    private long id;
    private String nama;
    private String harga;
    private String jumlah;

    public Barang() {
    }

    public Barang(String nama, String harga, String jumlah) {
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getJumlah(){
        return jumlah;
    }

    public void setJumlah(String jumlah){
        this.jumlah = jumlah;
    }

}
