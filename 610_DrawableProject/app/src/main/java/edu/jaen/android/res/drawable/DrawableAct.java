package edu.jaen.android.res.drawable;

import edu.jaen.android.res.drawable.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DrawableAct extends Activity {
    /** Called when the activity is first created. */
	LinearLayout mLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        mLinearLayout = new LinearLayout(this);
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.my_image);
        i.setAdjustViewBounds(false); 
        mLinearLayout.addView(i); 
       setContentView(mLinearLayout);
    }
}