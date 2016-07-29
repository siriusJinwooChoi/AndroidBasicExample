package com.example.edu.tabletest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sunbtn = (Button)findViewById(R.id.btnSunshine);
        Button cloudbtn = (Button)findViewById(R.id.btnCloudy);
        Button invibtn = (Button)findViewById(R.id.invi);

        View.OnClickListener listener = new View.OnClickListener() {
            ImageView imgsun = (ImageView)findViewById(R.id.imageSun);
            ImageView imgcloud = (ImageView)findViewById(R.id.imageCloud);
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btnSunshine) {
                    imgsun.setVisibility(View.VISIBLE);
                    imgcloud.setVisibility(View.INVISIBLE);
                } else if(v.getId() == R.id.btnCloudy) {
                    imgcloud.setVisibility(View.VISIBLE);
                    imgsun.setVisibility(View.INVISIBLE);
                } else if(v.getId() == R.id.invi) {
                    Log.d("invi start", "tksjldkajf");
                    imgcloud.setVisibility(View.INVISIBLE);
                    imgsun.setVisibility(View.INVISIBLE);
                }
            }
        };

        sunbtn.setOnClickListener(listener);
        cloudbtn.setOnClickListener(listener);
        invibtn.setOnClickListener(listener);
    }
}
