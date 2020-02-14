package com.nore.kalkulator_gampang.Model;

public class Barang {
    private long id;
    private String nama;
    private String harga;
<<<<<<< HEAD
    private  String jumlah;
=======
    private int jumlah;
>>>>>>> 3af55a4608c5b1a5596d938e6290d7e8400cfa5d

    public Barang() {
    }

    public Barang(String nama, String harga, int jumlah) {
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

<<<<<<< HEAD
    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
=======
    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
>>>>>>> 3af55a4608c5b1a5596d938e6290d7e8400cfa5d
        this.jumlah = jumlah;
    }
}
