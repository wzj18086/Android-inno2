package com.topcoder.innovate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.topcoder.innovate.R.id.webview;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView mywebview=(WebView) findViewById(webview);
        //设置webview
        String url=getResources().getString(R.string.baidu);
        mywebview.getSettings().setJavaScriptEnabled(true);
        mywebview.loadUrl(url);
        mywebview.setWebViewClient(new HelloWebViewClient ());
    }
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.home:
                Intent i = new Intent(this,HomeActivity.class);
                startActivity(i);
        }
    }
}
