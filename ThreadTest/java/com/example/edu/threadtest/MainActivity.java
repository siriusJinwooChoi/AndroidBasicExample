package com.example.edu.threadtest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    TextView txt;
    int cnt;

    Handler handler = new Handler() {       //handler의 소유자는 메인.
//        public void handleMessage(android.os.Message msg) {
//            if(msg.what == 0) {
//                int count = msg.arg1;
//                txt.setText(count + "");
//            }
//        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        txt = (TextView)findViewById(R.id.txt);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "btn1 Clicked", Toast.LENGTH_LONG).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread t = new Thread(){
                    @Override
                    public void run() {
                        for(int i=0; i<100; i++) {
                            cnt = i;
                            handler.post(new Runnable() {
                                public void run() {
                                    txt.setText(cnt + "");
                                }
                            });

//                            //txt.setText(i + "");            //숫자로 들어가면 안되서 숫자로 바꿔줌.
//                            //Message msg = new Message();
//                            //Message msg = Message.obtain();
//                            Message msg = handler.obtainMessage();          //객체를 직접생성하는 것보다 좋음!(맨윗줄의 new Message)
//                            msg.what = 0;
//                            msg.arg1 = i;
//                            //handler.sendMessage(msg);
//                            handler.sendMessageDelayed(msg, 2000+(long)(i*500));
//                            //handler.sendMessageAtTime(msg, uptimeMillis);
//                            handler.sendMessageAtFrontOfQueue(msg);
//                            //handler.sendEmptyMessage(0);      //0은 what의 값임
//
//                            Log.d("thread", "count:" + i);
                            try {
                                Thread.sleep(500);
                            }catch(InterruptedException e) {

                            }
                        }
                    }
                };
                t.start();
            }
        });
    }
}
