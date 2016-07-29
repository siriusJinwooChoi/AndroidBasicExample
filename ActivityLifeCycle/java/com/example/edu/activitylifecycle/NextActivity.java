package com.example.edu.activitylifecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class NextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("cycle", "NextActivity.onCreate");
        Toast.makeText(this, "NextActivity.onCreate", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_next);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("cycle", "NextActivity.onStart");
        Toast.makeText(this, "NextActivity.onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("cycle", "NextActivity.onResume");
        Toast.makeText(this, "NextActivity.onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("cycle", "NextActivity.onRestart");
        Toast.makeText(this, "NextActivity.onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("cycle", "NextActivity.onPause");
        Toast.makeText(this, "NextActivity.onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("cycle", "NextActivity.onStop");
        Toast.makeText(this, "NextActivity.onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cycle", "NextActivity.onDestroy");
        Toast.makeText(this, "NextActivity.onDestroy", Toast.LENGTH_SHORT).show();
    }
}
