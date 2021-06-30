package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.example.myapplication.R;

public class WebPage extends AppCompatActivity{
    private WebView webView;
    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_web);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/index.html"); // ローカルのhtmlファイルを指定

        // WebView内のJavaScriptの実行を許可
        webView.getSettings().setJavaScriptEnabled(true);
    }
}
