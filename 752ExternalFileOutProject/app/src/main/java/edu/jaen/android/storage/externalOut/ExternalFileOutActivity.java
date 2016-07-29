package edu.jaen.android.storage.externalOut;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ExternalFileOutActivity extends Activity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        TextView t=(TextView) findViewById(R.id.text1);
        
        String state = Environment.getExternalStorageState();
        //test
        Log.i("info", "1234: state="+state);
        
        if (Environment.MEDIA_MOUNTED.equals(state)) {    
	        BufferedWriter br=null;
	        try {
	        	//API Level 7 or lower
	        	//File f=new File(Environment.getExternalStorageDirectory(),"data.txt"); ///mnt/sdcard/data.txt
	        	
	        	//API Level 8 or greater
	        	System.out.println("dir = "+getExternalFilesDir(null));
	        	File f=new File(getExternalFilesDir(null),"data.txt"); ///mnt/sdcard/Android/data/<package_name>/files/data.txt
	        	
	        	br= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));            
	            br.append("안녕하세요요.");
	            br.append("자앤입니다. 반갑습니다.");
	            br.flush();
	            t.setText("파일이 정상적으로 생성되었습니다..");
	         } catch (Exception e) {
	            Log.e("IO", "File Output Error");
	            t.setText("파일 생성시 오류가 발생했습니다.");
	        } finally{
	        	try{
	        		if(br!=null) br.close();
	        	}catch(IOException ioe){ioe.printStackTrace();}
	        }
        }
        
    }
}