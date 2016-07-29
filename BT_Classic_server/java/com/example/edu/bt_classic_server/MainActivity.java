package com.example.edu.bt_classic_server;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_BT_ENABLE = 1;
    private static final int REQ_DEVICE_SCAN = 2;
    Button btnSend, btnScan, btnDiscorverable;
    EditText edtMsg;
    TextView txtMsg;

    BluetoothAdapter btAdt;
    BluetoothDevice btDevice;
    BluetoothSocket socket;
    InputStream btIn;
    OutputStream btOut;
    private static final UUID MY_UUID = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66"); // Custom
    boolean btEnabled = false, btAvailable = false, btConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = (Button)findViewById(R.id.btnSend);
        /*btnScan = (Button)findViewById(R.id.btnScan);*/
        btnDiscorverable = (Button)findViewById(R.id.btnShowme);
        edtMsg = (EditText)findViewById(R.id.edtMsg);
        txtMsg = (TextView)findViewById(R.id.txtMsg);

        btAdt = BluetoothAdapter.getDefaultAdapter();

        if(btAdt == null){
            txtMsg.setText("Sorry! Your device has not Bluetooth function.");
            return;
        }
        btAvailable = true;

        if(!btAdt.isEnabled()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQ_BT_ENABLE);
        }else{
            btEnabled = true;
        }

        listenTask.execute();
        btnDiscorverable.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btAdt.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE){
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                    intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                    startActivity(intent);
                }
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btConnected){
                    String msg = edtMsg.getText().toString();
                    try {
                        btOut.write(msg.getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }



    AsyncTask<Void, String, Void> listenTask = new AsyncTask<Void, String, Void>() {
        BluetoothServerSocket btServer = null;
        byte[] buff = new byte[1024];
        int bytes =0;
        boolean running = true;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            try {
                btServer = btAdt.listenUsingRfcommWithServiceRecord("ChatServer", MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
                txtMsg.append(e.getMessage() + "\n");
            }

        }

        @Override
        protected Void doInBackground(Void... params) {
            while(running) {
                try {
                    socket = btServer.accept();
                    btIn = socket.getInputStream();
                    btOut = socket.getOutputStream();
                    btConnected = true;
                    while(running){
                        bytes = btIn.read(buff);
                        String msg = new String(buff, 0, bytes);
                        publishProgress(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    publishProgress(e.getMessage());
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtMsg.append(values[0] + "\n");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            running = false;

        }
    };
}
