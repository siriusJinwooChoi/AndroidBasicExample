package com.example.edu.touchevent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt = (TextView)findViewById(R.id.text);
        txt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("event", "text x : " + event.getX() +", y:"+event.getY());
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("event", "text action:down");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("event", "text action:move");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("event", "text action:up");
                        break;
                    default:
                        Log.d("event", "text action:unknown");
                }
                return true;
            }
        });
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("event", "x : " + event.getX() +", y:"+event.getY());
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("event", "action:down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("event", "action:move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("event", "action:up");
                break;
            default:
                Log.d("event", "action:unknown");

        }
        Log.d("event", "action:" + event.getAction());

        return super.onTouchEvent(event);
    }
}
