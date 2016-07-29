package edu.jaen.android.res.shake;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class HelloGridView extends Activity {
    /** Called when the activity is first created. */
	GridView gridview ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //String hello = getString(R.string.hello);
        String hello = getResources().getString(R.string.hello);
        Log.d("res", hello);
        Toast.makeText(this, hello, Toast.LENGTH_LONG);

       gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }
    
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private Integer[] mThumbIds = {
                R.drawable.apple, R.drawable.banana,
                R.drawable.bananatwins, R.drawable.bench,
                R.drawable.cdnew, R.drawable.coffeetime,
                R.drawable.graphicsapplication, R.drawable.hotdogcar,
                R.drawable.icecream, R.drawable.ic_launcher,
                R.drawable.mail, R.drawable.marmaladecubes,
                R.drawable.mightyducks, R.drawable.mycomputer,
                R.drawable.mypictures, R.drawable.orange,
                R.drawable.pear, R.drawable.peartwo,
                R.drawable.peasheart, R.drawable.shoe,
                R.drawable.umbrella, R.drawable.trashempty
        };
        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
        menu.add(0,1,0, "shake");
        return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		gridview.startAnimation(AnimationUtils.loadAnimation(this,R.anim.shake));
		return super.onOptionsItemSelected(item);
	}

}