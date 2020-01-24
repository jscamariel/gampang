package com.nore.kalkulator_gampang;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public  class  NamaViewHolder extends  RecyclerView.ViewHolder{


        TextView nama;
        //TextView harga;

        public NamaViewHolder(android.view.View view) {

            super(view);
            nama = (TextView) itemView.findViewById(R.id.edit_nama);
            //harga = (TextView) itemView.findViewById(R.id.edit_harga);

        }
    }

    private ArrayList nama;
    //private ArrayList harga;

    public  recycleview (ArrayList nama){ //ArrayList harga
        this.nama=nama;
        //this.harga=harga;
    }
}
