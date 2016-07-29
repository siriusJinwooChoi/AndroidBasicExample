package com.example.edu.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webview = (WebView)findViewById(R.id.webview);
        final EditText edtMsg = (EditText)findViewById(R.id.edtMsg);

        //btn을 클릭했을 때에, javascript의 fromAndroid함수를 사용함. 아래에서 webview.addJavascript...를 통해 context를 JsObject로 넘겨주고, 거기서 토스트창으로 출력해줌.
        Button btnSend = (Button)findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = edtMsg.getText().toString();
                //from android()함수를 사용하겠다는 뜻.
                webview.loadUrl("javascript:fromAndroid('"+msg+"')");
            }
        });
        webview.addJavascriptInterface(new JsObject(this), "android");



        //javascript enable해주는 부분
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);

        //새창뜨지 못하도록 하기 위하여, 상속받아 구현해서 webviewClient에게 넣어줌.
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //원래는 false로 되어있는데, true로 바꿔주면 내가 직접 로딩할것이라는 이야기.(next.html클릭 시, 로딩될 때)
                //return super.shouldOverrideUrlLoading(view, url);
                view.loadUrl(url);
                return true;
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            //바깥 부분에 대한 메시지를 추가해줌.(console메시지를 웹브라우저에서 확인하기위해 만들어줌.)
            public boolean onConsoleMessage(ConsoleMessage cm) {
                Log.d("webview", cm.message() +
                        "(" + cm.sourceId() +
                        ":" + cm.lineNumber() +
                        ")");
                return super.onConsoleMessage(cm);
            }

            /*@Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Js Alert")
                        .setMessage(message)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("NO", null)
                        .setNeutralButton("CANCEL", null)
                        .show();
                return super.onJsAlert(view, url, message, result);
            }*/
        });


        //webview.loadUrl("http://m.naver.com");
        //webview.loadUrl("http://192.168.10.20:8087");
        //webview.loadUrl("http://192.168.10.6:8080");
        webview.loadUrl("file:///android_asset/www/index.html");

        /*webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(500);
                Toast.makeText(MainActivity.this, "vibrate", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
