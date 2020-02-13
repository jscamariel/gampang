package com.nore.kalkulator_gampang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nore.kalkulator_gampang.Model.Barang;
import com.nore.kalkulator_gampang.Utils.DatabaseHelper;

public class InputActivity extends AppCompatActivity {
    private EditText isi_nama;
    private EditText isi_harga;
    private EditText isi_jumlah;
    private Button btn_ok;
    private Button btn_cancel;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        //dbHelper = new DatabaseHelper(this);
        isi_nama = (EditText) findViewById(R.id.isi_nama) ;
        isi_harga = (EditText) findViewById(R.id.isi_harga) ;
        isi_jumlah = (EditText) findViewById(R.id.isi_jumlah) ;
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
         /*
                boolean isInserted = BantuDb.insertData(isi_nama.getText().toString(),isi_harga.getText().toString());
                //if(isInserted == true)
                //    Toast.makeText(InputActivity.this,"Data Tersimpan",Toast.LENGTH_LONG).show();
                //else
                //    Toast.makeText(InputActivity.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();

                if (isInserted==true){
                    Intent ganti = new Intent(InputActivity.this,MainActivity.class);
                    //ganti.putExtra("nama_barang",isi_nama);
                    //ganti.putExtra("harga_barang",isi_harga);
                    startActivity(ganti);
                }
        */
                saveBarang();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackHome();
            }
        });
    }

    private void saveBarang(){
        String nama = isi_nama.getText().toString().trim();
        String harga = isi_harga.getText().toString().trim();
        String jumlah = isi_jumlah.getText().toString().trim();
        dbHelper = new DatabaseHelper(this);

        if(nama.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }

        if(harga.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a price", Toast.LENGTH_SHORT).show();
        }

        if(jumlah.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a quantity", Toast.LENGTH_SHORT).show();
        }


        //create new person
        int jml = Integer.parseInt(jumlah);
        Barang barang = new Barang(nama, harga, jml);
        dbHelper.saveNewBarang(barang);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();
    }
    private void goBackHome(){
        startActivity(new Intent(InputActivity.this, MainActivity.class));
    }
}
