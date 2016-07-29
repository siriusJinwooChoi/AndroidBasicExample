package com.example.edu.intenttest;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, TargetActivity.class);
                Intent intent = new Intent();
                //intent.setClass(MainActivity.this, TargetActivity.class);
                ComponentName cp = new ComponentName("com.example.edu.intenttest", "com.example.edu.intenttest.TargetActivity");
                intent.setComponent(cp);
                // intent.setClassName("com.example.edu.intenttest", "com.example.edu.intenttest.TargetActivity");
                // intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                //intent.setClassName("com.example.edu.lineartest", "com.example.edu.lineartest.MainActivity");
                //intent.setAction(Intent.ACTION_VIEW);
                intent.setAction("banjang");
                intent.addCategory("my");
                intent.setData(Uri.parse("http://www.naver.com"));
                intent.putExtra("id", "android");
                intent.putExtra("pwd", "apple");
//                intent.setAction("bubanjang");
                //startActivity(intent);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String result = data.getStringExtra("result");
        if(resultCode == RESULT_OK) {
            Toast.makeText(this, requestCode + "," + resultCode + "," + result, Toast.LENGTH_LONG).show();
        }
    }
}
