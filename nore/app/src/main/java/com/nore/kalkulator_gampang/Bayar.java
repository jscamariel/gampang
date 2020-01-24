package com.nore.kalkulator_gampang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bayar extends AppCompatActivity {
    TextView totalbayar;
    Button seratus;
    Button duaratus;
    Button limaratus;
    Button seribu;
    Button duaribu;
    Button limaribu;
    Button sepuluhribu;
    Button duapuluhribu;
    Button limapuluhribu;
    Button seratusribu;
    TextView jml1,jml2,jml3,jml4,jml5,jml6,jml7,jml8,jml9,jml10;
    int qty1 = 0;
    int qty2 = 0;
    int qty3 = 0;
    int qty4 = 0;
    int qty5 = 0;
    int qty6 = 0;
    int qty7 = 0;
    int qty8 = 0;
    int qty9 = 0;
    int qty10 = 0;
    TextView kembalian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        totalbayar = (TextView) findViewById(R.id.totalbayar);
        seratus = (Button) findViewById(R.id.seratus);
        duaratus = (Button) findViewById(R.id.duaratus);
        limaratus = (Button) findViewById(R.id.limaratus);
        seribu = (Button) findViewById(R.id.seribu);
        duaribu = (Button) findViewById(R.id.duaribu);
        limaribu = (Button) findViewById(R.id.limaribu);
        sepuluhribu = (Button) findViewById(R.id.sepuluhribu);
        duapuluhribu = (Button) findViewById(R.id.duapuluhribu);
        limapuluhribu = (Button) findViewById(R.id.limapuluhribu);
        seratusribu = (Button) findViewById(R.id.seratusribu);
        jml1=(TextView) findViewById(R.id.jml1);
        jml2=(TextView) findViewById(R.id.jml2);
        jml3=(TextView) findViewById(R.id.jml3);
        jml4=(TextView) findViewById(R.id.jml4);
        jml5=(TextView) findViewById(R.id.jml5);
        jml6=(TextView) findViewById(R.id.jml6);
        jml7=(TextView) findViewById(R.id.jml7);
        jml8=(TextView) findViewById(R.id.jml8);
        jml9=(TextView) findViewById(R.id.jml9);
        jml10=(TextView) findViewById(R.id.jml10);
        kembalian = (TextView) findViewById(R.id.kembalian);

        seratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty1++;
                jml1.setText(""+qty1);
            }
        });

        jml1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty1!=0){
                    qty1--;
                    jml1.setText(""+qty1);
                }
            }
        });

        duaratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty2++;
                jml2.setText(""+qty2);
            }
        });

        jml2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty2!=0){
                    qty2--;
                    jml2.setText(""+qty2);
                }
            }
        });

        limaratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty3++;
                jml3.setText(""+qty3);
            }
        });

        jml3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty3!=0){
                    qty3--;
                    jml3.setText(""+qty3);
                }
            }
        });

        seribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty4++;
                jml4.setText(""+qty4);
            }
        });

        jml4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty4!=0){
                    qty4--;
                    jml4.setText(""+qty4);
                }
            }
        });

        duaribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty5++;
                jml5.setText(""+qty5);
            }
        });

        jml5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty5!=0){
                    qty5--;
                    jml5.setText(""+qty5);
                }
            }
        });

        limaribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty6++;
                jml6.setText(""+qty6);
            }
        });

        jml6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty6!=0){
                    qty6--;
                    jml6.setText(""+qty6);
                }
            }
        });

        sepuluhribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty7++;
                jml7.setText(""+qty7);
            }
        });

        jml7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty7!=0){
                    qty7--;
                    jml7.setText(""+qty7);
                }
            }
        });

        duapuluhribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty8++;
                jml8.setText(""+qty8);
            }
        });

        jml8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty8!=0){
                    qty8--;
                    jml8.setText(""+qty8);
                }
            }
        });

        limapuluhribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty9++;
                jml9.setText(""+qty9);
            }
        });

        jml9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty9!=0){
                    qty9--;
                    jml9.setText(""+qty9);
                }
            }
        });

        seratusribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty10++;
                jml10.setText(""+qty10);
            }
        });

        jml10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty10!=0){
                    qty10--;
                    jml10.setText(""+qty10);
                }
            }
        });
    }
}