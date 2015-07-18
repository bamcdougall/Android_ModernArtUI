package com.nsci_consulting.www.modernartui;

import android.app.Activity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowserActivity extends Activity {

    WebView mWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        mWebView = (WebView) findViewById(R.id.webview);

        // Set a kind of listener on the WebView so the WebView can intercept
        // URL loading requests if it wants to

        mWebView.setWebViewClient(new HelloWebViewClient());

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.moma.org");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class HelloWebViewClient extends WebViewClient {
        private static final String TAG = "HelloWebViewClient";;

        // Give application a chance to catch additional URL loading requests
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "About to load:" + url);
            view.loadUrl(url);
            return true;
        }
    }

}