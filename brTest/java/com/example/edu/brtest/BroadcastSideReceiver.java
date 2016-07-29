package com.example.edu.brtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by edu on 2016-03-23.
 */
public class BroadcastSideReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("FILE_NAME");
        Toast.makeText(context, name, Toast.LENGTH_LONG).show();
    }
}
