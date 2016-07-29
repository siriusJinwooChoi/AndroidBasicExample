package com.example.edu.bt_classic_client;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
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
    private static final UUID MY_UUID = UUID.fromString("fa87c0d0-afac-11de-8a39-0800200c9a66");

    Button btnSend, btnScan;
    TextView txtMsg;
    EditText edtMsg;

    BluetoothSocket socket;
    InputStream in;
    OutputStream out;

    BluetoothAdapter btAdt;
    boolean btAvailable, btEnabled, btConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSend = (Button)findViewById(R.id.btnSend);
        btnScan = (Button)findViewById(R.id.btnScan);
        txtMsg = (TextView)findViewById(R.id.txtMsg);
        edtMsg = (EditText)findViewById(R.id.edtMsg);

        btAdt = BluetoothAdapter.getDefaultAdapter();

        //bluetooth를 지원하지 않는 장치일 때,(btAdt == null일떄)
        if(btAdt == null) {
            txtMsg.setText("Sorry!, your device has not BT Function.");
            return;
        } else {
            btAvailable = true;
        }


        //bluetooth가 켜져있는지 꺼져있는지 알 수 있음
        if(!btAdt.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);     //intent에 bluetooth 관련된 action을 넣어줌.
            startActivityForResult(intent, 0);
        } else {
            btEnabled = true;
        }

        //scan시, 새로운 화면을 띄워줘야 함.
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bt가 사용 가능할 때만 띄워주도록 해야함.
                if(btAvailable && btEnabled) {
                    Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                    startActivityForResult(intent, 1);      //(intent, requestCode) --> (parameter) //ActivityForResult는 intent를 requestCode를 통하여 보내주어 기다림.(아래의 onActivityResult함수와 세트임!!)
                }
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btAvailable && btEnabled && btConnected) {
                    String msg = edtMsg.getText().toString();
                    try {
                        out.write(msg.getBytes());
                        txtMsg.append("sent:" + msg + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                        txtMsg.append("sent error:"+e.getMessage()+"\n");
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {     //intent로 data가 들어옴.
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {       //제대로 켜져있게 됨.
                btEnabled = true;
            } else {                        //제대로 못킨상태.
                txtMsg.append("BT is turned off. You need to turn on!!");
                btEnabled = false;
            }
        } else if(requestCode == 1) {
            //result가 ok일때만 처리해줘야 함.
            if(resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String address = data.getStringExtra("address");
                txtMsg.append("Did choose a device to connect.\n");

                BluetoothDevice device = btAdt.getRemoteDevice(address);

                //uuid는 접속대상의 프로토콜임.
                try {
                    socket = device.createRfcommSocketToServiceRecord(MY_UUID);
                    socket.connect();
                    in = socket.getInputStream();
                    out = socket.getOutputStream();
                    txtMsg.append("Connected successfully.\n");
                    btConnected = true;
                } catch (IOException e) {
                    e.printStackTrace();
                    txtMsg.append(e.getMessage() + "\n");
                    rTask.execute();
                    btConnected = false;
                }
            } else {        //아니라면 선택을 안하고 종료했다는 뜻.
                txtMsg.append("Did not choose a device.");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rTask.cancel(true);
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Thread rThread= new Thread(){
    AsyncTask<Void, String, Void> rTask = new AsyncTask<Void, String, Void>() {
        boolean running = true;
        byte[] buffer = new byte[1024];
        int bytes;


        @Override
        protected Void doInBackground(Void... params) {
            while(running){
                try {
                    bytes = in.read(buffer);
                    publishProgress(new String(buffer, 0, bytes));
                } catch (IOException e) {
                    e.printStackTrace();
                    publishProgress(e.getMessage());
                    break;
                }

            }

            return null;
        }
        @Override
        protected void onProgressUpdate(String ... values) {
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