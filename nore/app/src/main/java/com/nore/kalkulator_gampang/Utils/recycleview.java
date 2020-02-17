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

import com.nore.kalkulator_gampang.Bayar;
import com.nore.kalkulator_gampang.EditActivity;
import com.nore.kalkulator_gampang.MainActivity;
import com.nore.kalkulator_gampang.OnItemDeletedListener;
import com.nore.kalkulator_gampang.OnUpdateTotalListener;
import com.nore.kalkulator_gampang.R;
import com.nore.kalkulator_gampang.Model.Barang;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


public class recycleview extends RecyclerView.Adapter<recycleview.ViewHolder> {
    private List<Barang> mBarangList;
    private Context mContext;
    private RecyclerView mRecyclerV;

    int totalkosong;
    int qty;
    int qty1 = 0;
    int qty2 = 0;
    int val1, val2, amunt1, amunt2;
    int totale = 0;
    int hargane = 0;

    //baru delete
    private OnItemDeletedListener onItemDeletedListener;

    private OnUpdateTotalListener mListener;


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
        public Button tombolbayar;
        public Button bersihkan ;

        public ViewHolder(View v) {
                super(v);
                layout = v;
                barangNamaTxtV = (Button) v.findViewById(R.id.edit_nama);
                barangHargaTxtV = (TextView) v.findViewById(R.id.edit_harga);
                barangMinus = (Button) v.findViewById(R.id.btn_minus);
                quantity = (TextView) v.findViewById(R.id.qty);
                barangTambah = (Button) v.findViewById(R.id.btn_plus);
                totalHargaTxtV = (TextView) v.findViewById(R.id.total);
                tombolbayar = (Button) v.findViewById(R.id.bayar);
                bersihkan = (Button) v.findViewById(R.id.btn_reset);
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
    public recycleview(List<Barang> myDataset, Context context, RecyclerView recyclerView, OnUpdateTotalListener listener) {
        mBarangList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
        //baru
        mListener=listener;
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
            Locale localeID = new Locale("in", "ID");
            final NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            //onClick
            setOnUpdateTotalListener(mListener);
                    holder.totalHargaTxtV.setText("Total Harga : "+formatRupiah.format((int)grandTotal()));

            holder.tombolbayar.setEnabled(false);
            if(grandTotal()!=0){
                holder.tombolbayar.setEnabled(grandTotal()!=0);
            }
            holder.tombolbayar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent membayar = new Intent(mContext, Bayar.class);
                    membayar.putExtra("total",grandTotal());
                    mContext.startActivity(membayar);
                }
            });

            holder.bersihkan.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    totalkosong = grandTotal();
                    for(int i=0; i<mBarangList.size(); i++){
                        totalkosong=0;
                        //qty = mBarangList.get(i).getJumlah();
                        qty = 0;
                        mBarangList.get(i).setJumlah(qty);
                    }


                    holder.totalHargaTxtV.setText("Total Harga : "+totalkosong);
                    //holder.quantity.setText(""+qty);
                }
            });
        }
        else {
            // disini
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            final Barang barang = mBarangList.get(position);
            holder.barangNamaTxtV.setText("" + barang.getNama());
            holder.quantity.setText(""+barang.getJumlah());
            holder.barangHargaTxtV.setText(""+formatRupiah.format((int)Integer.parseInt(barang.getHarga())));

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
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(mContext);
                            builder2.setTitle("Konfirmasi");
                            builder2.setMessage("Anda yakin menghapus data?");
                            builder2.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
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

                            builder2.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });

                            //disini
                            builder2.create().show();

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
                    barang.setJumlah(barang.getJumlah()+1);
                    holder.quantity.setText(""+barang.getJumlah());
                    //Toast.makeText(mContext,"id ke : "+barang.getId(),Toast.LENGTH_SHORT).show();
                }
            });

            holder.barangMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(barang.getJumlah()!=0){
                        barang.setJumlah(barang.getJumlah()-1);
                        holder.quantity.setText(""+barang.getJumlah());
                    }


                    //Toast.makeText(mContext,"id ke : "+barang.getId(),Toast.LENGTH_SHORT).show();
                }
            });
        }

        // - get element from your dataset at this position
        // - replace the contents of the view with that element


        //Compare size and add button at buttom of view,ie arraylist size


        //holder.quantity.setText(""+barang.getJumlah());
        //holder.barangHargaTxtV.setText("Rp." + barang.getHarga());
        //baruuuuu

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



    public int grandTotal(){
        int i;
        int totalPrice = 0;
        for(i = 0 ; i < mBarangList.size(); i++) {
            totalPrice += Integer.parseInt(mBarangList.get(i).getHarga()) * mBarangList.get(i).getJumlah();
        }
        //baru
        mListener.onUpdateTotal();
        return  totalPrice;
    }

    public void meh_nambahi(){

        for(int i=0; i<mBarangList.size(); i++){
            mBarangList.get(i).setJumlah(mBarangList.get(i).getJumlah()+1);
        }

    }

    public void meh_ngurangi(){

        for(int i=0; i<mBarangList.size(); i++){
            mBarangList.get(i).setJumlah(mBarangList.get(i).getJumlah()-1);
        }
    }

    public int harga_qty(){
        for(int i=0; i<mBarangList.size(); i++){
            hargane = qty1* Integer.parseInt(mBarangList.get(i).getHarga());
        }
        return hargane;
    }

    /*
    public void reset_qty(){
        int i;
        for(i=0; i<mBarangList.size(); i++){
            qty = mBarangList.get(i).getJumlah();
            qty = 0;
        }
    }
    */

    //baru delete
    public void setOnItemDeletedListener(Object object) {
        onItemDeletedListener = (OnItemDeletedListener) object;
    }


    public void setOnUpdateTotalListener(Object object) {
        mListener = (OnUpdateTotalListener) object;
    }

    public interface OnUpdateTotalListener {
        void onUpdateTotal();
    }




    public void setNewPriceText(){
        grandTotal();
    }



    public void onUpdateTotal() {
        grandTotal();
    }
}
