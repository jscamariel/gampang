package com.nore.kalkulator_gampang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="gampang.db";
    public static final String TABLE_NAME="table_gampang";
    public static final String COL_1="nama_barang";
    public static final String COL_2="harga";

    public static final int DATABASE_VERTION=1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERTION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table table_gampang(nama_barang text null,harga text null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //metode untuk tambah data
    public boolean insertData(String nama_barang,String harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nama_barang);
        contentValues.put(COL_2, harga);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    //metode untuk mengambil data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from table_gampang", null);
        return res;
    }

    //metode untuk merubah data
    public boolean updateData(String nama_barang, String harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, nama_barang);
        contentValues.put(COL_2, harga);

        db.update(TABLE_NAME, contentValues, "nama_barang = ?", new String[]{nama_barang});
        return true;
    }

    //metode untuk menghapus data
    public int deleteData(String nama_barang) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "nama_barang = ?", new String[]{nama_barang});
    }
}
