package com.example.edu.receivertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by edu on 2016-03-21.
 */
public class PowerRecvr extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(Intent.ACTION_POWER_CONNECTED.equals(action)) {
            Log.d("pwr", "Power connected");
            Toast.makeText(context, "Power connected", Toast.LENGTH_SHORT).show();
        } else if(Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            Toast.makeText(context, "Power disconnected", Toast.LENGTH_SHORT).show();
        }
    }
}