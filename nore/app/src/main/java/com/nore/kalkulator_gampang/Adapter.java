package com.nore.kalkulator_gampang;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.app.AlertController;

public class Adapter extends AlertController.RecycleListView {
    public Adapter(Context context) {
        super(context);
    }

    public Adapter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
}
