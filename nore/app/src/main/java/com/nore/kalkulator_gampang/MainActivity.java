package com.nore.kalkulator_gampang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nore.kalkulator_gampang.Utils.DatabaseHelper;
import com.nore.kalkulator_gampang.Utils.recycleview;


import java.util.ArrayList;

//import static com.nore.kalkulator_gampang.Utils.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity  { //implements ExampleDialog.ExampleDialogListener
    //TextView tv_nama;
    //TextView tv_harga;
    Button btn_minus;
    Button btn_add;
    //TextView jml;
    //int qty = 0;
    Button btn_reset;

    TextView tv_total;

   //private ArrayList nama;
   //private ArrayList harga;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHelper dbHelper;
    private recycleview adapter;
    private String filter = "";

    //baruuuuu
    private int mSubTotal = 0;
    Context context ;

    Button bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize the variables
        mRecyclerView = (RecyclerView)findViewById(R.id.daftarBarang);
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //populate recyclerview
        populaterecyclerView(filter);

        /*
        dbHelper = new DatabaseHelper(this);

        nama = new ArrayList<>();
        harga = new ArrayList<>();
        Cursor res = BantuDb.getAllData();
        res.moveToFirst();
        for (int count = 0; count < res.getCount(); count++)
        {
            res.moveToPosition(count);
            nama.add(res.getString(0));
            harga.add(res.getString(1));
        }
        daftarBrg.setAdapter(new recycleview(nama,harga)); //harga
        //daftarBrg.setAdapter(new Adapter(nama));
        */

        //tv_nama=(TextView) findViewById(R.id.edit_nama); //Button
        //tv_harga=(TextView) findViewById(R.id.edit_harga);
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
        //jml = (TextView) findViewById(R.id.qty); ///tadi salah
        //jml.setText(""+qty);
        btn_reset=(Button) findViewById(R.id.btn_reset);
        tv_total = (TextView) findViewById(R.id.total);

        bayar = (Button) findViewById(R.id.bayar);

        //baruuuuu
        //bayar.setVisibility(View.INVISIBLE);

        //    bayar.setVisibility(1);


        //tv_nama.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        qty++;
        //        jml.setText(""+qty);
        //    }
        //});

        //btn_minus.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        if(qty!=0){
        //            qty--;
        //            jml.setText(""+qty);
        //        }
        //    }
        //});





        //btn_reset.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v){
        //        qty = 0 ;
        //        jml.setText(""+qty);
        //    }
        //});

        //tv_total.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        showMessage("Detail Pembayaran",tv_nama.getText().toString());
        //    }
        //});

        Intent ganti = getIntent();


        //String nama_barang = ganti.getStringExtra("isi_barang");
        //String harga_barang = ganti.getStringExtra("isi_harga");
        //tv_nama.setText(nama_barang);

        //boolean isInserted = BantuDb.insertData(tv_nama.getText().toString(),tv_harga.getText().toString());


        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
                adapter.reset_qty();
                //Cursor res = BantuDB.getData(xnim.getText().toString());
            }
        });

        Intent kembali = getIntent();

        //baruuuuu
        mSubTotal = adapter.grandTotal(); //harusnya getTotal(a,b);
        tv_total.setText("Total Harga:" + String.valueOf(mSubTotal));

        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent membayar = new Intent(MainActivity.this,Bayar.class);
                membayar.putExtra("total",mSubTotal);
                startActivity(membayar);
            }
        });

    }

    //public void openDialog(){
    //    ExampleDialog exampleDialog = new ExampleDialog();
    //    exampleDialog.show(getSupportFragmentManager(),"example dialog");
    //}

    //@Override
    //public  void applyTexts(String nama, String harga){
    //    tv_nama.setText(nama);
    //    tv_harga.setText(harga);
    //}

    //public void showMessage(String title, String message){
    //    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    //    builder.setCancelable(true);
    //    builder.setTitle(title);
    //    builder.setMessage(message);
        //builder.setMessage(tv_nama.getText().toString());
    //    builder.show();
    //}

    private void populaterecyclerView(String filter){
        dbHelper = new DatabaseHelper(this);
        adapter = new recycleview(dbHelper.barangList(filter), this, mRecyclerView);
        mRecyclerView.setAdapter(adapter);
    }

    public void reset(){
        tv_total.setText("Total Harga:");
    }

}
