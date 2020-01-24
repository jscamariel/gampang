package com.nore.kalkulator_gampang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycleview extends RecyclerView.Adapter<recycleview.NamaViewHolder>{
    @NonNull
    @Override
    public NamaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        android.view.View view = inflater.inflate(R.layout.recyclerview,viewGroup,false);
        return new NamaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NamaViewHolder namaViewHolder, int i) {
        String NAMA = (String) nama.get(i);
        //String HARGA = (String) harga.get(i);

        namaViewHolder.nama.setText(NAMA);
        //namaViewHolder.harga.setText(HARGA);
    }

    @Override
    public int getItemCount() {
        return nama.size();
    }

    public  class  NamaViewHolder extends RecyclerView.ViewHolder{
        Button nama;
        //TextView harga;
        TextView jml;
        int qty = 0;
        Button minus;
        DatabaseHelper BantuDb;

        public NamaViewHolder(android.view.View view) {
            super(view);
            //BantuDb = new DatabaseHelper(this);
            nama = (Button) itemView.findViewById(R.id.edit_nama);
            //harga = (TextView) itemView.findViewById(R.id.edit_harga);
            jml = (TextView) itemView.findViewById(R.id.qty);
            minus = (Button) itemView.findViewById(R.id.btn_minus);

            nama.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    qty++;
                    jml.setText(""+qty);
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(qty!=0){
                        qty--;
                        jml.setText(""+qty);
                    }
                }
            });

            nama.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int res = BantuDb.deleteData(nama.getText().toString());
                    return false;
                }
            });
        }
    }

    private ArrayList nama;
    //private ArrayList harga;

    public  recycleview (ArrayList nama){ //ArrayList harga
        this.nama=nama;
        //this.harga=harga;
    }

    public interface OnNoteListener{
        void onNoteClick(int postion);
    }
}
