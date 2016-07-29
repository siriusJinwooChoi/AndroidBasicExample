package com.example.edu.receivertest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by edu on 2016-03-21.
 */
public class Mp3Service extends Service {
    MediaPlayer player = null;

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.sensitive);     //이 음악을 만들어 줄 것을 player로 만들어줘.
        Log.d("mp3", "onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(player != null)
            player.start();
        Log.d("mp3", "onStart");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        if(player != null)
            player.stop();
        Log.d("mp3", "onDestory");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
