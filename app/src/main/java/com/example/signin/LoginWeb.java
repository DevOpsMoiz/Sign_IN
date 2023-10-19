package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.util.Log;
import android.widget.Button;

public class LoginWeb extends AppCompatActivity {
    Button acc, del;
    private WebView myWebView;
    SwipeRefreshLayout SW;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        acc = findViewById(R.id.accept);
        del = findViewById(R.id.Decline);
        SW = findViewById(R.id.webview); // Initialize the SwipeRefreshLayout

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(LoginWeb.this, Login.class);
                startActivity(t);
            }
        });

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myWebView = findViewById(R.id.webview);
        myWebView.setWebViewClient(new MyWebClient());
        myWebView.loadUrl("https://doc-hosting.flycricket.io/signup-privacy-policy/3ab00320-363e-4258-a8dd-fc0775963149/privacy");
        Log.d("MyWebViewActivity", "onCreate called");

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set up a listener for the SwipeRefreshLayout
        SW.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myWebView.reload(); // Reload the WebView when the user performs a swipe-to-refresh
            }
        });

        // Add a Handler to refresh the web page after 3 seconds
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                myWebView.reload();
                SW.setRefreshing(false); // Stop the SwipeRefreshLayout
            }
        }, 3000); // 3000 milliseconds (3 seconds)
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
