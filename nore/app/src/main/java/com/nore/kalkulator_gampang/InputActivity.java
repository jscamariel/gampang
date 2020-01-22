package com.nore.kalkulator_gampang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.nore.kalkulator_gampang.DatabaseHelper.TABLE_NAME;

public class InputActivity extends AppCompatActivity {
    EditText isi_nama;
    EditText isi_harga;
    Button ok;
    String input_nama;
    String input_harga;
    DatabaseHelper BantuDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        BantuDb = new DatabaseHelper(this);
        isi_nama = (EditText) findViewById(R.id.nama) ;
        isi_harga = (EditText) findViewById(R.id.harga) ;
        ok = (Button) findViewById(R.id.btn_ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                input_nama = isi_nama.getText().toString();
                input_harga = isi_harga.getText().toString();

                boolean isInserted = BantuDb.insertData(input_nama,input_harga);
                //if(isInserted == true)
                //    Toast.makeText(InputActivity.this,"Data Tersimpan",Toast.LENGTH_LONG).show();
                //else
                //    Toast.makeText(InputActivity.this,"Data Gagal Tersimpan",Toast.LENGTH_LONG).show();

                Intent ganti = new Intent(InputActivity.this,MainActivity.class);
                ganti.putExtra("nama_barang",input_nama);
                ganti.putExtra("harga_barang",input_harga);
                startActivity(ganti);
            }
        });
    }
}
