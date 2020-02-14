package com.nore.kalkulator_gampang.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.nore.kalkulator_gampang.Model.Barang;

import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "gampang.db";
    private static final int DATABASE_VERSION = 3 ;
    public static final String TABLE_NAME = "table_gampang";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BARANG_NAMA = "nama";
    public static final String COLUMN_BARANG_HARGA = "harga";
    public static final String COLUMN_BARANG_JUMLAH = "jumlah";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BARANG_NAMA + " TEXT NOT NULL, " +
                COLUMN_BARANG_HARGA + " TEXT NOT NULL, " +
<<<<<<< HEAD
                COLUMN_BARANG_JUMLAH + " TEXT NOT NULL );"
=======
                COLUMN_BARANG_JUMLAH + " INTEGER NOT NULL );"
>>>>>>> 3af55a4608c5b1a5596d938e6290d7e8400cfa5d
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    /**create record**/
    public void saveNewBarang(Barang barang) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_BARANG_NAMA, barang.getNama());
        values.put(COLUMN_BARANG_HARGA, barang.getHarga());
        values.put(COLUMN_BARANG_JUMLAH, barang.getJumlah());

        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    /**Query records, give options to filter results**/
    public List<Barang> barangList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }

        List<Barang> barangLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Barang barang;

        if (cursor.moveToFirst()) {
            do {
                barang = new Barang();

                barang.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                barang.setNama(cursor.getString(cursor.getColumnIndex(COLUMN_BARANG_NAMA)));
                barang.setHarga(cursor.getString(cursor.getColumnIndex(COLUMN_BARANG_HARGA)));
                barang.setJumlah(cursor.getInt(cursor.getColumnIndex(COLUMN_BARANG_JUMLAH)));
                barangLinkedList.add(barang);
            } while (cursor.moveToNext());
        }


        return barangLinkedList;
    }

    /**Query only 1 record**/
    public Barang getBarang(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Barang receivedBarang = new Barang();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedBarang.setNama(cursor.getString(cursor.getColumnIndex(COLUMN_BARANG_NAMA)));
            receivedBarang.setHarga(cursor.getString(cursor.getColumnIndex(COLUMN_BARANG_HARGA)));
            receivedBarang.setJumlah(cursor.getInt(cursor.getColumnIndex(COLUMN_BARANG_JUMLAH)));
        }

        return receivedBarang;
    }


    /**delete record**/
    public void deleteBarangRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id='"+id+"'");
        Toast.makeText(context, "Berhasil dihapus.", Toast.LENGTH_SHORT).show();

    }

    /**update record**/
    public void updateBarangRecord(long barangId, Context context, Barang updatedbarang) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLE_NAME+" SET nama ='"+ updatedbarang.getNama() + "', harga ='"+ updatedbarang.getHarga() + "', jumlah ='" + updatedbarang.getJumlah()+ "'  WHERE _id='" + barangId + "'");
        Toast.makeText(context, "Berhasil diperbarui.", Toast.LENGTH_SHORT).show();

    }
}
