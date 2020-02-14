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

public class EditActivity extends AppCompatActivity {
    private EditText barangNamaUpdate;
    private EditText barangHargaUpdate;
    private EditText barangJumlahUpdate;
    private Button mUpdateBtn;
    private  Button btn_cancel;

    private DatabaseHelper dbHelper;
    private long receivedBarangId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //init
        barangNamaUpdate = (EditText)findViewById(R.id.barangNamaUpdate);
        barangHargaUpdate = (EditText)findViewById(R.id.barangHargaUpdate);
        barangJumlahUpdate = (EditText)findViewById(R.id.barangJumlahUpdate);
        mUpdateBtn = (Button)findViewById(R.id.updateBarangButton);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        dbHelper = new DatabaseHelper(this);

        try {
            //get intent to get person id
            receivedBarangId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***populate user data before update***/
        Barang queriedBarang = dbHelper.getBarang(receivedBarangId);
        //set field to this user data
        barangNamaUpdate.setText(queriedBarang.getNama());
        barangHargaUpdate.setText(queriedBarang.getHarga());
        barangJumlahUpdate.setText(queriedBarang.getJumlah());


        //listen to add button click to update
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                updateBarang();
                goBackHome();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBackHome();
            }
        });
    }

    private void updateBarang(){
        String nama = barangNamaUpdate.getText().toString().trim();
        String harga = barangHargaUpdate.getText().toString().trim();
        String jumlah = barangJumlahUpdate.getText().toString().trim();

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

        //create updated person
        int jml = Integer.parseInt(jumlah);
        Barang updatedBarang = new Barang(nama, harga, jml);

        //call dbhelper update
        dbHelper.updateBarangRecord(receivedBarangId, this, updatedBarang);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete

    }

    private void goBackHome(){
        startActivity(new Intent(EditActivity.this, MainActivity.class));
    }

}
