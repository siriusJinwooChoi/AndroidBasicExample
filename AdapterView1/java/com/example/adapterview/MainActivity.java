package com.example.adapterview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listview = (ListView)findViewById(R.id.listview);
		final String[] data = {"one", "two", "three", "four", "five", "six", "seven"};
		MyAdapter adt = new MyAdapter(this, data, R.layout.list);
//		ArrayAdapter<String> adt = 
				new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
		listview.setAdapter(adt);
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				//Toast.makeText(MainActivity.this, position + data[position] + "clicked", Toast.LENGTH_SHORT).show();
				//String label = ((TextView)view).getText().toString();
				String label = (String) parent.getAdapter().getItem(position);
				Toast.makeText(MainActivity.this, position + label + " clicked" + id, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	

}
