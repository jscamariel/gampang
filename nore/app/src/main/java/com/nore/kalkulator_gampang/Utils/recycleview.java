package com.nore.kalkulator_gampang.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.session.MediaSessionManager;
import android.net.sip.SipAudioCall;
import android.net.sip.SipSession;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.nore.kalkulator_gampang.EditActivity;
import com.nore.kalkulator_gampang.MainActivity;
import com.nore.kalkulator_gampang.OnItemDeletedListener;
import com.nore.kalkulator_gampang.R;
import com.nore.kalkulator_gampang.Model.Barang;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class recycleview extends RecyclerView.Adapter<recycleview.ViewHolder>{
    private List<Barang> mBarangList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    int qty = 0;
    int qty1 = 0;
    int qty2 = 0;
    int val1, val2, amunt1, amunt2;
    int totale = 0;
    int hargane = 0;

    //baru delete
    private OnItemDeletedListener onItemDeletedListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button barangNamaTxtV;
        public TextView barangHargaTxtV;
        public View layout;
        public Button barangMinus;
        public  Button barangTambah;
        public TextView quantity;
        public  TextView totalHargaTxtV;
        public ViewHolder(View v) {
                super(v);
                layout = v;
                barangNamaTxtV = (Button) v.findViewById(R.id.edit_nama);
                barangHargaTxtV = (TextView) v.findViewById(R.id.edit_harga);
                barangMinus = (Button) v.findViewById(R.id.btn_minus);
                quantity = (TextView) v.findViewById(R.id.qty);
                barangTambah = (Button) v.findViewById(R.id.btn_plus);
                totalHargaTxtV = (TextView) v.findViewById(R.id.total);
        }
    }

    //hai
    @Override
    public int getItemViewType(int position) {
        return (position == mBarangList.size()) ? R.layout.activity_tampilintotal : R.layout.recyclerview;
    }

    public void add(int position, Barang barang) {
        mBarangList.add(position, barang);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mBarangList.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public recycleview(List<Barang> myDataset, Context context, RecyclerView recyclerView) {
        mBarangList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public recycleview.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v ; // = inflater.inflate(R.layout.recyclerview, parent, false);
        //hai
        if(viewType == R.layout.recyclerview){
            v = inflater.inflate(R.layout.recyclerview, parent, false);
        }

        else {
            v = inflater.inflate(R.layout.activity_tampilintotal, parent, false);
        }

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //hai
        if(position == mBarangList.size()) {
            //textview
            holder.totalHargaTxtV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.totalHargaTxtV.setText("Total Harga : "+grandTotal());
                }
            });
        }
        else {
            final Barang barang = mBarangList.get(position);
            holder.barangNamaTxtV.setText("" + barang.getNama());
            holder.quantity.setText(""+qty1);
            holder.barangHargaTxtV.setText(""+barang.getHarga());

            //yg lama
            holder.barangNamaTxtV.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Silahkan pilih:");
                    builder.setMessage("Perbarui atau hapus data?");
                    builder.setPositiveButton("Perbarui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            //go to update activity
                            goToUpdateActivity(barang.getId());

                        }
                    });
                    builder.setNeutralButton("Hapus", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseHelper dbHelper = new DatabaseHelper(mContext);


                            dbHelper.deleteBarangRecord(barang.getId(), mContext);

                            mBarangList.remove(position);
                            mRecyclerV.removeViewAt(position);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, mBarangList.size());
                            notifyDataSetChanged();
                            //if(mContext instanceof MainActivity){
                            //    grandTotal();
                            //}

                            //baruuuuu
                            //if (position == mBarangList.size()-1) {
                            //    grandTotal();
                            //}
                            onItemDeletedListener.onItemDeleted();

                        }
                    });
                    builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                    return true;
                }
            });

            holder.barangTambah.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.quantity.setText(""+meh_nambahi());
                    Toast.makeText(mContext,"id ke : "+barang.getId(),Toast.LENGTH_SHORT).show();
                }
            });

            holder.barangMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.quantity.setText(""+meh_ngurangi());
                    Toast.makeText(mContext,"id ke : "+barang.getId(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        //Compare size and add button at buttom of view,ie arraylist size


        //holder.quantity.setText(""+barang.getJumlah());
        //holder.barangHargaTxtV.setText("Rp." + barang.getHarga());
        //baruuuuu
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        //holder.barangHargaTxtV.setText(formatRupiah.format((int)Integer.parseInt(barang.getHarga())));

        //listen on long click

        //baruuu
        //getTotal(Integer.parseInt(barang.getHarga()),qty);
    }

    private void goToUpdateActivity(long barangId){
        Intent goToUpdate = new Intent(mContext, EditActivity.class);
        goToUpdate.putExtra("USER_ID", barangId);
        mContext.startActivity(goToUpdate);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //hai ndk pake +1
        return mBarangList.size()+1;
    }


    // baruuu
    public int getTotal(int amount, int value){
        int quantity = value;
        int totalamount;
        int amountall = amount;
        totalamount = value * amount;
        grandTotal();
        return totalamount;
    }

    public int grandTotal(){
        int i;
        int totalPrice = 0;
        for(i = 0 ; i < mBarangList.size(); i++) {
            totalPrice += Integer.parseInt(mBarangList.get(i).getHarga())*qty1;
        }
        System.out.println("totale: "+totalPrice);
        return  totalPrice;
    }

    public int meh_nambahi(){
        for(int i = 0; i<mBarangList.size(); i++){
            qty1++;
        }

        return qty1;
    }

    public int meh_ngurangi(){
        for(int i = 0; i<mBarangList.size(); i++){
            qty1--;
        }
        return qty1;
    }

    public int harga_qty(){
        for(int i=0; i<mBarangList.size(); i++){
            hargane = qty1* Integer.parseInt(mBarangList.get(i).getHarga());
        }
        return hargane;
    }


    public void reset_qty(){
        int i;
        for(i=0; i<mBarangList.size(); i++){
            qty = Integer.parseInt(mBarangList.get(i).getJumlah());
            qty = 0;
        }
    }

    //baru delete
    public void setOnItemDeletedListener(Object object) {
        onItemDeletedListener = (OnItemDeletedListener) object;
    }


}
