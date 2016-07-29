package com.example.edu.simpleadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

        for(int i=0; i<50; i++) {
            HashMap<String, String> member = new HashMap<String ,String>();
            member.put("name", "Lee"+i);
            member.put("g", "Lee"+i);
            data.add(member);
        }
        int[] viewId = {R.id.name, R.id.age};
        String[] dataKey = {"name", "age"};

        SimpleAdapter adt = new SimpleAdapter(this, data, R.layout.activity_main, dataKey, viewId);
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(adt);
    }
}
