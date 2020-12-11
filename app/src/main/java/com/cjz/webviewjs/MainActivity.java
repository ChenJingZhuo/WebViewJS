package com.cjz.webviewjs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_dialog);
        final WebView webview = (WebView) findViewById(R.id.webView);
        webview.loadUrl("file:///android_asset/alert.html"); //指定要加载的网页
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setVisibility(View.GONE);
                //设置webview控件支持JavaScript代码
                webview.getSettings().setJavaScriptEnabled(true);
                //显示网页中通过JavaScript代码弹出的提示框
                webview.setWebChromeClient(new WebChromeClient());
                //webview.loadUrl("file:///android_asset/alert.html");
                webview.loadUrl("file:///android_asset/index.html");
                WebSettings webSetting = webview.getSettings();
                webSetting.setAllowFileAccess(true);
                webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
                webSetting.setSupportZoom(true);
                webSetting.setBuiltInZoomControls(true);
                webSetting.setUseWideViewPort(true);
                webSetting.setSupportMultipleWindows(false);
                webSetting.setAppCacheEnabled(true);
                webSetting.setDomStorageEnabled(true);
                webSetting.setDefaultTextEncodingName("utf-8");
                webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出游戏",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
