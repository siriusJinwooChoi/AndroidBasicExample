package com.example.edu.menutest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt = (TextView)findViewById(R.id.txt);
        registerForContextMenu(txt);            //txt에게 context메뉴를 띄우게 해달라는것임.
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {           //View v 로 누가 눌렸는 지 알수있게 함.
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "menu-0");
        menu.add(0, 1, 0, "menu-1");
        menu.add(0, 2, 0, "menu-2");
        menu.add(0, 3, 0, "menu-3");
        menu.add(0, 4, 0, "menu-4");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {                   //길게 누르면, 메뉴를 띄우게해줌
        Toast.makeText(this, item.getItemId() + ":" + item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {     //안드로이드의 메뉴를 구성하게 함.
        menu.add(0, 0, 0, "menu-0");
        menu.add(0, 1, 0, "menu-1");
        menu.add(0, 2, 0, "menu-2");
        menu.add(0, 3, 0, "menu-3");
        menu.add(0, 4, 0, "menu-4");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {           //위에서 메뉴가 눌리면 호출되게 함.
        if(item.getItemId() == 0) {             //0번 ID의 메뉴가 눌렸을 때!
            //item.setShowAsAction();
            Toast.makeText(this, "Menu-0 selected", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, item.getItemId() + ":" + item.getTitle(), Toast.LENGTH_SHORT).show();


        return super.onOptionsItemSelected(item);
    }
}
