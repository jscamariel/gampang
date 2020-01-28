package com.nore.kalkulator_gampang;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.nore.kalkulator_gampang.Utils.DatabaseHelper;

public class ExampleDialog extends AppCompatDialogFragment {
    EditText edit_nama;
    EditText edit_harga;
    ExampleDialogListener listener;
    DatabaseHelper BantuDb;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //BantuDb=new DatabaseHelper(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog,null);
        builder.setView(view)
                .setTitle("Masukkan data")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nama = edit_nama.getText().toString();
                        String harga = edit_harga.getText().toString();
                        listener.applyTexts(nama,harga);
                        BantuDb=new DatabaseHelper(getContext());
                        //boolean isInserted = BantuDb.insertData(edit_nama.getText().toString(),edit_harga.getText().toString());
                    }
                });

        edit_nama = view.findViewById(R.id.edit_nama);
        edit_harga = view.findViewById(R.id.edit_harga);
        //BantuDb=new DatabaseHelper(MainActivity.class);
        return builder.create();
    }
    @Override
    public  void onAttach(Context context){
        super.onAttach(context);
        try{
            listener = (ExampleDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement ExampleDialogListener");
        }

    }
    public interface ExampleDialogListener{
        void applyTexts(String nama, String harga);
    }
}
