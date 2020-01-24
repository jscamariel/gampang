package com.nore.kalkulator_gampang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Bayar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);
    }


    public class NamaViewHolder extends RecyclerView.ViewHolder {
        Button nama;
        //TextView harga;
        TextView jml;
        int qty = 0;
        Button minus;
        DatabaseHelper BantuDb;

        public NamaViewHolder(android.view.View view) {
            super(view);
            nama = (Button) itemView.findViewById(R.id.edit_nama);
            //harga = (TextView) itemView.findViewById(R.id.edit_harga);
            jml = (TextView) itemView.findViewById(R.id.qty);

            nama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    qty++;
                    jml.setText("" + qty);
                }
                }
            });

        }
    }
}