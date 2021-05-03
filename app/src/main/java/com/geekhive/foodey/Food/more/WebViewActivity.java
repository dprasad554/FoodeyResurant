package com.geekhive.foodey.Food.more;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.geekhive.foodey.R;

public class WebViewActivity extends Activity {

    String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webUrl = getIntent().getStringExtra("weburl");

        WebView browser =  findViewById(R.id.webview);
        browser.loadUrl(webUrl);
    }
}
