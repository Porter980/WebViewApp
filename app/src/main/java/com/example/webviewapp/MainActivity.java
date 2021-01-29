package com.example.webviewapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.webviewapp.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {

    private WebView webview;
    private ProgressBar spinner;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webview =(WebView)findViewById(R.id.webView);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        webview.setWebViewClient(new CustomWebViewClient());

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setDomStorageEnabled(true);
        webview.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webview.loadUrl("http://site.com");
    }

        private class CustomWebViewClient extends WebViewClient {

            @Override
            public void onPageStarted(final WebView webview, String url, Bitmap favicon) {
               webview.setVisibility(webview.INVISIBLE);
               new Handler().postDelayed((new Runnable() {
                   @Override
                   public void run() {
                       spinner.setVisibility(View.GONE);
                       webview.setVisibility(webview.VISIBLE);
                   }
               }), 5000);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                spinner.setVisibility(View.GONE);
                view.setVisibility(webview.VISIBLE);
                super.onPageFinished(view, url);

            }
        }
    }
