package com.example.edu.intenttest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        String info = "TargetActivity";
        Intent intent = getIntent();

        String action = intent.getAction();
        Set<String> categories = intent.getCategories();
        Uri data = intent.getData();
        Bundle bundle = intent.getExtras();

        info += "\n action : "+ action;
        info += "\n categoris : " + (categories !=null ? categories.size() : 0);
        if(categories != null){
            for(String category: categories){
                info += "\n\t "+ category;
            }
        }
        info += "\n data : " + (data != null ? data.toString() : "null");
        info += "\n bundle : "+ (bundle != null ? bundle.size() : "null");
        if(bundle != null){
            for(String key : bundle.keySet()){
                info += "\t\n key:"+ key + " value:"+ bundle.getString(key);
            }
        }

        String id = intent.getStringExtra("id");
        String pwd = intent.getStringExtra("pwd");
        //int pwd = intent.getIntExtra(("pwd"); 만약 int형으로 전달되었다면!

        Toast.makeText(this, "id:"+id +", pwd:"+pwd, Toast.LENGTH_SHORT).show();

        textView.setText(info);
        setContentView(textView);

        String result = "Hello";
        Intent rIntent = new Intent();
        rIntent.putExtra("result", result);
        setResult(RESULT_OK, rIntent);
    }
}
