package com.example.edu.threadtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button btn1, btn2;
    TextView txt;

    class MyTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        public Void doInBackground(Void[] params) {             //본게임들어가서만 쓰레드가 동작됨.
            for(int i=0; i<100; i++) {
                publishProgress(i);                         //호출해주면 아래 onProgressUpdate가 실행됨.
                try {
                    Thread.sleep(500);
                } catch(InterruptedException e) {

                }

            }
            return null;
        };

        protected void onProgressUpdate(Integer ... values) {       //실제 진행중인 처리
            txt.setText(values[0] + "");
        };
        protected void onPostExecute(Void result) {

        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        txt = (TextView)findViewById(R.id.txt);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this, "btn1 Clicked", Toast.LENGTH_LONG).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                task.execute();
            }
        });
    }
}
