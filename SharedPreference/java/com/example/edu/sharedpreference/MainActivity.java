package com.example.edu.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    String filename = "myfile.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);
        final EditText edt = (EditText)findViewById(R.id.edt);

        //꺼내와서 보여주는 부분
        String saved = getPreferences(Context.MODE_PRIVATE).getString("edit", "no edit");
        edt.setText(saved);

        //file을 꺼내와서 토스트로 보여줌
        try {
            FileInputStream fin = openFileInput(filename);
            byte[] buffer = new byte[1024];
            try {
                int read = fin.read(buffer);
                String content = new String(buffer, 0, read);
                Toast.makeText(this, content, Toast.LENGTH_LONG).show();

            } catch(IOException e) {
                e.printStackTrace();
            }
        } catch(FileNotFoundException e1) {
            e1.printStackTrace();

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edt.getText().toString();
                Toast.makeText(MainActivity.this, "saving :" + content, Toast.LENGTH_SHORT).show();
                //저장해주는 부분
                SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
                sp.edit().putString("edit", content).commit();
            }
        });

        Button btn2 = (Button)findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String filename = "myfile.txt";
//                FileOutputStream fout = new FileOutputStream(filename);
                try {
                    FileOutputStream fout = openFileOutput(filename, Context.MODE_PRIVATE);
                    String content = edt.getText().toString();
                    fout.write(content.getBytes());
                    fout.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
