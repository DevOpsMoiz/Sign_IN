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

public class SignUpWeb extends AppCompatActivity {
    Button acc, del;
    private WebView myWebView1;
    SwipeRefreshLayout SW;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_web);
        acc = findViewById(R.id.accpt);
        del = findViewById(R.id.Declne);
        SW = findViewById(R.id.Swipe);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y = new Intent(SignUpWeb.this, MainActivity.class);
                startActivity(y);
                finish();
            }
        });

        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myWebView1 = findViewById(R.id.webview1);
        myWebView1.setWebViewClient(new MyWebClient());
        myWebView1.loadUrl("https://doc-hosting.flycricket.io/signup-privacy-policy/3ab00320-363e-4258-a8dd-fc0775963149/privacy");
        Log.d("MyWebViewActivity", "onCreate called");

        WebSettings webSettings = myWebView1.getSettings();
        webSettings.setJavaScriptEnabled(true);

        SW.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myWebView1.reload();
            }
        });

        // Add a Handler to refresh the web page after 3 seconds
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myWebView1.reload();
                        SW.setRefreshing(false); // Stop the SwipeRefreshLayout
                    }
                });
            }
        }, 3000); // 3000 milliseconds (3 seconds)
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            SW.setRefreshing(false); // Stop the SwipeRefreshLayout when the web page has finished loading
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (myWebView1.canGoBack()) {
            myWebView1.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
