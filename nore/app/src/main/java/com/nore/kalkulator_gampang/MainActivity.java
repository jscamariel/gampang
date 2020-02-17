package com.nore.kalkulator_gampang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nore.kalkulator_gampang.Utils.DatabaseHelper;
import com.nore.kalkulator_gampang.Utils.recycleview;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

//import static com.nore.kalkulator_gampang.Utils.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity implements OnItemDeletedListener { //implements ExampleDialog.ExampleDialogListener
    //TextView tv_nama;
    //TextView tv_harga;
    Button btn_minus;
    Button btn_add;
    Button btn_reset;
    //TextView jml;
    //int qty = 0;


    //TextView tv_total;

   //private ArrayList nama;
   //private ArrayList harga;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private DatabaseHelper dbHelper;
    private recycleview adapter;
    private String filter = "";

    //baruuuuu
    private int mSubTotal =0;
    Context context ;

    Button bayar;

    Locale localeID = new Locale("in", "ID");
    final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);


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
                goToAddActivity();
            }
        });
        btn_reset = (Button) findViewById(R.id.btn_reset);
        //jml = (TextView) findViewById(R.id.qty); ///tadi salah
        //jml.setText(""+qty);
        //tv_total = (TextView) findViewById(R.id.total);



        //baruuuuu
        //bayar.setVisibility(View.INVISIBLE);

        //    bayar.setVisibility(1);
        //bayar.setEnabled(false);


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



        Intent kembali = getIntent();

        //baruuuuu
        //mSubTotal = adapter.grandTotal();

        //if (mSubTotal != 0) {
            //tv_total.setText("Total Harga: "+formatRupiah.format((int)mSubTotal));
        //    bayar.setEnabled(mSubTotal!=0);
        //}
        //else {
            //tv_total.setText("Total Harga: 0");
        //}
        //mSubTotal = adapter.grandTotal(); //harusnya getTotal(a,b);



        //tv_total.setText("Total Harga:" + String.valueOf(mSubTotal));




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
        adapter = new recycleview(dbHelper.barangList(filter), this, mRecyclerView, new recycleview.OnUpdateTotalListener() {
            @Override
            public void onUpdateTotal() {
                //adapter.grandTotal();

            }
        });
        //baru delete
        adapter.setOnItemDeletedListener(this);
        mRecyclerView.setAdapter(adapter);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add:
                goToAddActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToAddActivity(){
        Intent tambah=new Intent(MainActivity.this,InputActivity.class);
        startActivity(tambah);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    //baru delete
    @Override
    public void onItemDeleted() {
        mSubTotal = adapter.grandTotal();
        if (mSubTotal != 0) {
            //tv_total.setText("Total Harga: "+formatRupiah.format((int)mSubTotal));
            //bayar.setEnabled(mSubTotal!=0);
        }
        else {
            //tv_total.setText("Total Harga: 0");
            //bayar.setEnabled(false);
        }
    }
}
