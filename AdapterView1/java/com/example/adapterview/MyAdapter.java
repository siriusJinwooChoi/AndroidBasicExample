package com.example.adapterview;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	String[] data;
	Context ctx;
	int res;
	
	MyAdapter(Context ctx, String[] data, int res){
		this.ctx = ctx;
		this.data = data;
		this.res = res;
	}

	@Override
	public int getCount() {
		return data.length;
		//return 100;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout linear;
		TextView txt1;
		TextView txt2;
		
		if(convertView != null){
			linear = ((LinearLayout)convertView);
			txt1 = ((MyTag)linear.getTag()).txt1;
			txt2 = ((MyTag)linear.getTag()).txt2;
		}else{
//			linear = new LinearLayout(MainActivity.this);
//			txt1 = new TextView(MainActivity.this);
//			txt2 = new TextView(MainActivity.this);
//			txt1.setId(R.id.txt1);
//			txt1.setId(R.id.txt2);
//			linear.addView(txt1);
//			linear.addView(txt2);
			linear = (LinearLayout) View.inflate(ctx, res, null);
			txt1 = (TextView)linear.findViewById(R.id.txt1);
			txt2 = (TextView)linear.findViewById(R.id.txt2);
			MyTag tag = new MyTag();
			tag.txt1 = txt1;
			tag.txt2 = txt2;
			linear.setTag(tag);
		}


		txt1.setText(position + 1 + "");
		txt2.setText(data[position]);
//		txt1.setTextSize(30);
//		txt2.setTextSize(30);
		if(position % 2 == 0){
			linear.setBackgroundColor(Color.GRAY);
		}else{
			linear.setBackgroundColor(Color.WHITE);
		}
		return linear;
	}
	
	@Override
	public Object getItem(int position) {
		return data[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

}
class MyTag{
	TextView txt1;
	TextView txt2;
}