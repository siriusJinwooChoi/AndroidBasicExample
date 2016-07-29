package com.example.edu.universe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CountryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Intent intent = getIntent();        //intent안에 있는 데이터를 불러와줌.

        ImageView flag = (ImageView)findViewById(R.id.flag);
        TextView name = (TextView)findViewById(R.id.name);
        TextView intro = (TextView)findViewById(R.id.intro);
        TextView people = (TextView)findViewById(R.id.people);
        TextView capital = (TextView)findViewById(R.id.capital);
        TextView area = (TextView)findViewById(R.id.area);

        flag.setImageResource(intent.getIntExtra("flag", 0));
        name.setText(intent.getStringExtra("name"));
        intro.setText(intent.getStringExtra("intro"));
        people.setText(intent.getIntExtra("people",0)+"");
        capital.setText(intent.getStringExtra("capital"));
        area.setText(intent.getDoubleExtra("area",0)+"");
    }
}