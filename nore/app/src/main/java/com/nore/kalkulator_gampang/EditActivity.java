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
    private Button mUpdateBtn;

    private DatabaseHelper dbHelper;
    private long receivedBarangId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //init
        barangNamaUpdate = (EditText)findViewById(R.id.barangNamaUpdate);
        barangHargaUpdate = (EditText)findViewById(R.id.barangHargaUpdate);
        mUpdateBtn = (Button)findViewById(R.id.updateBarangButton);

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


        //listen to add button click to update
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save person method
                updateBarang();
            }
        });
    }

    private void updateBarang(){
        String nama = barangNamaUpdate.getText().toString().trim();
        String harga = barangHargaUpdate.getText().toString().trim();

        if(nama.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }

        if(harga.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a price", Toast.LENGTH_SHORT).show();
        }

        //create updated person
        Barang updatedBarang = new Barang(nama, harga);

        //call dbhelper update
        dbHelper.updateBarangRecord(receivedBarangId, this, updatedBarang);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();
    }

    private void goBackHome(){
        startActivity(new Intent(EditActivity.this, MainActivity.class));
    }

}
