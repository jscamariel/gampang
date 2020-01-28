package com.nore.kalkulator_gampang.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.nore.kalkulator_gampang.MainActivity;
import com.nore.kalkulator_gampang.R;
import com.nore.kalkulator_gampang.Model.Barang;

import java.util.ArrayList;
import java.util.List;


public class recycleview extends RecyclerView.Adapter<recycleview.ViewHolder>{
    private List<Barang> mBarangList;
    private Context mContext;
    private RecyclerView mRecyclerV;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button barangNamaTxtV;
        public TextView barangHargaTxtV;

        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            barangNamaTxtV = (Button) v.findViewById(R.id.edit_nama);
            barangHargaTxtV = (TextView) v.findViewById(R.id.edit_harga);

        }
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
        View v =
                inflater.inflate(R.layout.recyclerview, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Barang barang = mBarangList.get(position);
        holder.barangNamaTxtV.setText("" + barang.getNama());
        holder.barangHargaTxtV.setText("Rp." + barang.getHarga());


        //listen on long click
        holder.barangNamaTxtV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete data?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        goToUpdateActivity(barang.getId());

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                        dbHelper.deleteBarangRecord(barang.getId(), mContext);

                        mBarangList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mBarangList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return false;
            }
        });

        //listen to single view layout click



    }

    private void goToUpdateActivity(long barangId){
        Intent goToUpdate = new Intent(mContext, MainActivity.class);
        goToUpdate.putExtra("USER_ID", barangId);
        mContext.startActivity(goToUpdate);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mBarangList.size();
    }
}
