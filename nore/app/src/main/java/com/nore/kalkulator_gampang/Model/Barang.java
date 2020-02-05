package com.nore.kalkulator_gampang.Model;

public class Barang {
    private long id;
    private String nama;
    private String harga;

    public Barang() {
    }

    public Barang(String nama, String harga) {
        this.nama = nama;
        this.harga = harga;
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

}
