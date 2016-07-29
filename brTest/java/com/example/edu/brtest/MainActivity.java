package com.example.edu.brtest;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    BroadcastSideReceiver rcv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);

        rcv = new BroadcastSideReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("FILE_DOWN");
        registerReceiver(rcv, filter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("FILE_DOWN");
                intent.putExtra("FILE_NAME", "superdroid.png");

                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(rcv);
    }
}
