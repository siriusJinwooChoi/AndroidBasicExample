package com.example.edu.tcpclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class MainActivity extends Activity {

    TextView txtMsg;
    EditText edtMsg;
    Button btnSend;

    Socket socket;
    InputStream in;
    OutputStream out;
    boolean running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtMsg = (TextView)findViewById(R.id.txtMsg);
        edtMsg = (EditText)findViewById(R.id.edtMsg);
        btnSend = (Button)findViewById(R.id.btn);

        Thread t = new Thread() {
            public void run() {
                try {
                    socket = new Socket("192.168.10.6", 5000);
                    //txtMsg.append("socket connected..\n");
                    handler.sendMessage(handler.obtainMessage(0, "socket connected.."));

                    in = socket.getInputStream();
                    out = socket.getOutputStream();

                    while(running) {
                        byte[] buff = new byte[1024];
                        int bytes = in.read(buff);          //읽어들인 buff의 개수를 읽어들임.(bytes는 문자열의 사이즈가 됨.)
                        String msg = new String(buff, 0, bytes);        //buff에 0번째 부터 bytes까지 문자열로 만들어라.
                        handler.sendMessage(handler.obtainMessage(0, msg));     //msg가 data가 될것임.
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendMessage(handler.obtainMessage(0, "err:" + e.getMessage()));
                }
            };
        };
        t.start();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edtMsg.getText().toString();
                if(out != null) {
                    try {
                        out.write(msg.getBytes());
                        handler.sendMessage(handler.obtainMessage(0, "send:"+msg));
                    } catch (IOException e) {
                        e.printStackTrace();
                        handler.sendMessage(handler.obtainMessage(0, e.getMessage()));
                    }
                }
            }
        });
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            txtMsg.append(msg.obj + "\n");
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        running = false;
        try {
            socket.close();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}