package com.nore.kalkulator_gampang;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

//import static com.nore.kalkulator_gampang.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity  { //implements ExampleDialog.ExampleDialogListener
    TextView tv_nama;
    TextView tv_harga;
    Button btn_minus;
    Button btn_add;
    TextView jml;
    int qty = 0;
    Button btn_reset;

    TextView tv_total;

   DatabaseHelper BantuDb;
   private ArrayList nama;
   //private ArrayList harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BantuDb = new DatabaseHelper(this);
        final RecyclerView daftarBrg = (RecyclerView) findViewById(R.id.daftarBarang);
        daftarBrg.setLayoutManager(new LinearLayoutManager(this));

        nama = new ArrayList<>();
        //harga = new ArrayList<>();
        Cursor res = BantuDb.getAllData();
        res.moveToFirst();
        for (int count = 0; count < res.getCount(); count++)
        {
            res.moveToPosition(count);
            nama.add(res.getString(0));
            //harga.add(res.getString(1));
        }
        daftarBrg.setAdapter(new recycleview(nama)); //harga
        //daftarBrg.setAdapter(new Adapter(nama));


        tv_nama=(TextView) findViewById(R.id.edit_nama); //Button
        tv_harga=(TextView) findViewById(R.id.edit_harga);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_add = (Button) findViewById(R.id.add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //openDialog();
                Intent tambah=new Intent(MainActivity.this,InputActivity.class);
                startActivity(tambah);
            }
        });
        jml = (TextView) findViewById(R.id.qty); ///tadi salah
        jml.setText(""+qty);
        btn_reset=(Button) findViewById(R.id.btn_reset);
        tv_total = (TextView) findViewById(R.id.total);

        tv_nama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty++;
                jml.setText(""+qty);
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty!=0){
                    qty--;
                    jml.setText(""+qty);
                }
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                qty = 0 ;
                jml.setText(""+qty);
            }
        });

        tv_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("Detail Pembayaran",tv_nama.getText().toString());
            }
        });

        Intent ganti = getIntent();
        String nama_barang = ganti.getStringExtra("nama_barang");
        String harga_barang = ganti.getStringExtra("harga_barang");
        //tv_nama.setText(nama_barang);

        //boolean isInserted = BantuDb.insertData(tv_nama.getText().toString(),tv_harga.getText().toString());

    }

    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

    //@Override
    //public  void applyTexts(String nama, String harga){
    //    tv_nama.setText(nama);
    //    tv_harga.setText(harga);
    //}

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        //builder.setMessage(tv_nama.getText().toString());
        builder.show();
    }

}
