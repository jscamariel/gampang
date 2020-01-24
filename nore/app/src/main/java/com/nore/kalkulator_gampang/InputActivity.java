package com.nore.kalkulator_gampang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {
    EditText isi_nama;
    EditText isi_harga;
    Button btn_ok;
    DatabaseHelper BantuDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        BantuDb = new DatabaseHelper(this);
        isi_nama = (EditText) findViewById(R.id.isi_nama) ;
        isi_harga = (EditText) findViewById(R.id.isi_harga) ;
        btn_ok = (Button) findViewById(R.id.btn_ok);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                }else{

                }

            }
        });
    }
}
