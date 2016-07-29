package com.example.edu.webview;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by edu on 2016-03-25.
 */
public class JsObject {
    //toast에서 쓸 context가 필요함.
    Context context;
    public JsObject(Context context) {
        this.context = context;
    }

    //toast라는 함수에 msg를 주면 토스트창으로 msg를 길게 출력해줌.
    @android.webkit.JavascriptInterface
    public void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    @android.webkit.JavascriptInterface
    public void beep() {
        Uri ringtoneUri = RingtoneManager.getActualDefaultRingtoneUri(context, RingtoneManager.TYPE_RINGTONE);
        Ringtone ringtone = RingtoneManager.getRingtone(context, ringtoneUri);
        ringtone.play();
    }
}
