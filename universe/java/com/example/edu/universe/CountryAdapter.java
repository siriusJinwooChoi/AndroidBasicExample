package com.example.edu.universe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by edu on 2016-03-24.
 */
class CountryAdapter extends BaseAdapter {
    Context context;
    ArrayList<Country> data;
    int layout;

    CountryAdapter(Context context, ArrayList<Country> data, int layout) {
        this.context = context;
        this.data = data;
        this.layout = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linear = null;
        ImageView flag = null;
        TextView name = null;
        TextView intro = null;

        if(convertView != null) {           //linear layout이 넘어올떄,(넘어온다는 뜻)
            linear = (LinearLayout)convertView;
            flag = (ImageView)linear.findViewById(R.id.flag);
            name = (TextView)linear.findViewById(R.id.name);
            intro = (TextView)linear.findViewById(R.id.intro);
        } else {
            linear = (LinearLayout)View.inflate(context, layout, null);
            flag = (ImageView)linear.findViewById(R.id.flag);
            name = (TextView)linear.findViewById(R.id.name);
            intro = (TextView)linear.findViewById(R.id.intro);
        }
        Country c = data.get(position);
        flag.setImageResource(c.flag);
        name.setText(c.name);
        intro.setText(c.intro);

        return linear;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    /*@Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }*/

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).flag;             //국기(long타입이므로)를 얻어옴
    }
}