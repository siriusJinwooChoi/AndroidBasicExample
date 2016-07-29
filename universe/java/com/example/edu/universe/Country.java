
package com.example.edu.universe;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Country extends BaseAdapter {
	String code;
	int flag;
	String name;
	String intro;
	int people;
	String capital;
	double area;

	public Country(String code, int flag, String name, String intro, int people, String capital, double area) {
		super();
		this.code = code;
		this.flag = flag;
		this.name = name;
		this.intro = intro;
		this.people = people;
		this.capital = capital;
		this.area = area;
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}
}