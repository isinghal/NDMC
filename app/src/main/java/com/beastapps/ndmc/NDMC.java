package com.beastapps.ndmc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class NDMC extends AppCompatActivity {
    ProgressBar progressBar;
    String ShowOrHideWebViewInitialUse = "show";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndmc);
        Intent myIntent = getIntent();
        String message = myIntent.getStringExtra("URL");
        WebView webView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        getSupportActionBar().setTitle(HomeAdapter.buttonNames[myIntent.getIntExtra("POS",0)]);
        if(message!=null)
        webView.loadUrl(message);
        else
        webView.loadUrl("http://www.ndmc.gov.in/ndmc/history.aspx");
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());

    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {


                return false;

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // only make it invisible the FIRST time the app is run
            if (ShowOrHideWebViewInitialUse.equals("show")) {
                view.setVisibility(view.INVISIBLE);
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            ShowOrHideWebViewInitialUse = "hide";
            progressBar.setVisibility(view.GONE);
            view.setVisibility(view.VISIBLE);
            super.onPageFinished(view, url);

        }
    }

}
