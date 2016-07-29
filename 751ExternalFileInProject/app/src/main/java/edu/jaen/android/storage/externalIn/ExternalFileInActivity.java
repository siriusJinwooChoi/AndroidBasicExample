package edu.jaen.android.storage.externalIn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.jaen.android.storage.externalIn.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

public class ExternalFileInActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView t=(TextView) findViewById(R.id.text1);
        
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {    
	        BufferedReader br=null;
	        try {
	        	//API Level 7 or lower
	        	//File f=new File(Environment.getExternalStorageDirectory(),"data.txt"); ///mnt/sdcard/data.txt
	        	
	        	//API Level 8 or greater
	        	Context pc=createPackageContext("edu.jaen.android.storage.externalOut", Context.CONTEXT_IGNORE_SECURITY);
	        	File f=new File(pc.getExternalFilesDir(null),"data.txt");///mnt/sdcard/Android/data/<package_name>/files/data.txt
	        	
	        	br= new BufferedReader(new InputStreamReader(new FileInputStream(f)));            
	            String msg=br.readLine();
	             while(msg !=null){
	            	t.append(msg+"\n");
	            	msg=br.readLine();
	            }
	        } catch (Exception e) {
	            Log.e("IO", "Exception");
			} finally{
	        	try{
	        		if(br!=null) br.close();
	        	}catch(IOException ioe){ioe.printStackTrace();}
	        }
        }
    }
}