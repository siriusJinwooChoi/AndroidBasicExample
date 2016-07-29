package com.example.edu.activitylifecycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("cycle", "MainActivity.onCreate");
        Toast.makeText(this, "MainActivity.onCreate", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NextActivity.class);
                startActivity(intent);
            }
        });
    }


    //액티비티 데이터 저장과 복원 생명주기
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("cycle", "MainActivity.onRestore");
        Toast.makeText(this, "MainActivity.onRestore", Toast.LENGTH_SHORT).show();
        String value = savedInstanceState.getString("key");
        Toast.makeText(this, value, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("key", "value");
        Log.d("cycle", "MainActivity.onSave");
        Toast.makeText(this, "MainActivity.onSave", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("cycle", "MainActivity.onStart");
        Toast.makeText(this, "MainActivity.onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("cycle", "MainActivity.onResume");
        Toast.makeText(this, "MainActivity.onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("cycle", "MainActivity.onRestart");
        Toast.makeText(this, "MainActivity.onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("cycle", "MainActivity.onPause");
        Toast.makeText(this, "MainActivity.onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("cycle", "MainActivity.onStop");
        Toast.makeText(this, "MainActivity.onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cycle", "MainActivity.onDestroy");
        Toast.makeText(this, "MainActivity.onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
