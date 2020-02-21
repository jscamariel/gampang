package com.nore.kalkulator_gampang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class Bayar extends AppCompatActivity implements OnKembalianListener{
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
    Button kurang1;
    Button kurang2;
    Button kurang3;
    Button kurang4;
    Button kurang5;
    Button kurang6;
    Button kurang7;
    Button kurang8;
    Button kurang9;
    Button kurang10;
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
    TextView textview5;

    //boolean clicked1 = false;
    TextView btn_kembalian;
    int change ,pricetotal, uang;
    int change1;
    int change2;
    int change3;
    int change4;
    int change5;
    int change6;
    int change7;
    int change8;
    int change9;
    int change10;
    int uang1;
    int uang2;
    int uang3;
    int uang4;
    int uang5;
    int uang6;
    int uang7;
    int uang8;
    int uang9;
    int uang10;

    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    //baru
    private OnKembalianListener onKembalianListener;

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
        kurang1 = (Button) findViewById(R.id.min1);
        kurang2 = (Button) findViewById(R.id.min2);
        kurang3 = (Button) findViewById(R.id.min3);
        kurang4 = (Button) findViewById(R.id.min4);
        kurang5 = (Button) findViewById(R.id.min5);
        kurang6 = (Button) findViewById(R.id.min6);
        kurang7 = (Button) findViewById(R.id.min7);
        kurang8 = (Button) findViewById(R.id.min8);
        kurang9 = (Button) findViewById(R.id.min9);
        kurang10 = (Button) findViewById(R.id.min10);
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

        btn_kembalian = (TextView) findViewById(R.id.btn_kembalian);

        textview5 = (TextView) findViewById(R.id.textView5);


        Intent membayar = getIntent();
        pricetotal = membayar.getIntExtra("total",0);
        //totalbayar.setText("Rp."+String.valueOf(pricetotal));
        totalbayar.setText(formatRupiah.format((int)pricetotal));

        seratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty1++;
                jml1.setText("" + qty1);
                //clicked1 = true;
                uang1 = 100*qty1;
                //change1 = uang1 - pricetotal;
                onKembalianListener.onKembalian();
            }
        });

        kurang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty1!=0){
                    qty1--;
                    jml1.setText(""+qty1);
                    uang1 = 100*qty1;
                    //change1 = uang1 - pricetotal;
                }
                onKembalianListener.onKembalian();
            }
        });

        duaratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty2++;
                jml2.setText(""+qty2);
                uang2 = 200*qty2;
                //change2 = uang2 - pricetotal;
                onKembalianListener.onKembalian();
            }
        });

        kurang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty2!=0){
                    qty2--;
                    jml2.setText(""+qty2);
                    uang2 = 200*qty2;
                    //change2 = uang2 - pricetotal;
                }
                onKembalianListener.onKembalian();
            }
        });

        //change = change1+change2;
        //uang = uang1+uang2;

        limaratus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty3++;
                jml3.setText(""+qty3);
                uang3 = 500*qty3;
                onKembalianListener.onKembalian();
            }
        });

        kurang3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty3!=0){
                    qty3--;
                    jml3.setText(""+qty3);
                    uang3 = 500*qty3;
                }
                onKembalianListener.onKembalian();
            }
        });

        seribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty4++;
                jml4.setText(""+qty4);
                uang4 = 1000*qty4;
                onKembalianListener.onKembalian();
            }
        });

        kurang4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty4!=0){
                    qty4--;
                    jml4.setText(""+qty4);
                    uang4 = 1000*qty4;
                }
                onKembalianListener.onKembalian();
            }
        });

        duaribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty5++;
                jml5.setText(""+qty5);
                uang5 = 2000*qty5;
                onKembalianListener.onKembalian();
            }
        });

        kurang5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty5!=0){
                    qty5--;
                    jml5.setText(""+qty5);
                    uang5 = 2000*qty5;
                }
                onKembalianListener.onKembalian();
            }
        });

        limaribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty6++;
                jml6.setText(""+qty6);
                uang6 = 5000*qty6;
                onKembalianListener.onKembalian();
            }
        });

        kurang6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty6!=0){
                    qty6--;
                    jml6.setText(""+qty6);
                    uang6 = 5000*qty6;
                }
                onKembalianListener.onKembalian();
            }
        });

        sepuluhribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty7++;
                jml7.setText(""+qty7);
                uang7 = 10000*qty7;
                onKembalianListener.onKembalian();
            }
        });

        kurang7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty7!=0){
                    qty7--;
                    jml7.setText(""+qty7);
                    uang7 = 10000*qty7;
                }
                onKembalianListener.onKembalian();
            }
        });

        duapuluhribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty8++;
                jml8.setText(""+qty8);
                uang8 = 20000*qty8;
                onKembalianListener.onKembalian();
            }
        });

        kurang8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty8!=0){
                    qty8--;
                    jml8.setText(""+qty8);
                    uang8 = 20000*qty8;
                }
                onKembalianListener.onKembalian();
            }
        });

        limapuluhribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty9++;
                jml9.setText(""+qty9);
                uang9 = 50000*qty9;
                onKembalianListener.onKembalian();
            }
        });

        kurang9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty9!=0){
                    qty9--;
                    jml9.setText(""+qty9);
                    uang9 = 50000*qty9;
                }
                onKembalianListener.onKembalian();
            }
        });

        seratusribu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qty10++;
                jml10.setText(""+qty10);
                uang10 = 100000*qty10;
                onKembalianListener.onKembalian();
            }
        });

        kurang10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty10!=0){
                    qty10--;
                    jml10.setText(""+qty10);
                    uang10 = 100000*qty10;
                }
                onKembalianListener.onKembalian();
            }
        });

        totalbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kemenu = new Intent(Bayar.this,MainActivity.class);
                startActivity(kemenu);
            }
        });
                //baruu
                setOnKembalianListener(this);
                change = (uang1+uang2+uang3+uang4+uang5+uang6+uang7+uang8+uang9+uang10)-pricetotal;
                if(uang1+uang2+uang3+uang4+uang5+uang6+uang7+uang8+uang9+uang10 < pricetotal){
                    kembalian.setText(" "+formatRupiah.format((int)change));
                }else{
                    //change = (uang1+uang2+uang3+uang4+uang5+uang6+uang7+uang8+uang9+uang10)-pricetotal;
                    kembalian.setText(formatRupiah.format((int)change));
                }



    }

    public void setOnKembalianListener(Object object) {
        onKembalianListener = (OnKembalianListener) object;
    }

    @Override
    public void onKembalian() {
        change = (uang1+uang2+uang3+uang4+uang5+uang6+uang7+uang8+uang9+uang10)-pricetotal;
        if(uang1+uang2+uang3+uang4+uang5+uang6+uang7+uang8+uang9+uang10 < pricetotal){
            kembalian.setText(" "+formatRupiah.format((int)change));
        }else{
            //change = (uang1+uang2+uang3+uang4+uang5+uang6+uang7+uang8+uang9+uang10)-pricetotal;
            kembalian.setText(" "+formatRupiah.format((int)change));
        }
    }
}