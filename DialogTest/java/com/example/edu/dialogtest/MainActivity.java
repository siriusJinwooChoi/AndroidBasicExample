package com.example.edu.dialogtest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("This is Title");
//                builder.setMessage("This is message");
//                builder.setPositiveButton("OK", null);
//                builder.setNegativeButton("NO", null);
//                builder.setNeutralButton("Cancel", null);
//                builder.setIcon(R.drawable.images);
//                AlertDialog dialog = builder.create();
//                dialog.show();
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("This is Title")
                        .setMessage("This is message")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "OK Clicked", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NO", null)
                        .setNeutralButton("Cancel", null)
                        //.setIcon(R.drawable.images)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        //.create();
                        .show();
            }
        });
    }
}
