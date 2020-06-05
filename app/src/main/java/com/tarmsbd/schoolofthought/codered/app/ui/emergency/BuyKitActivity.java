package com.tarmsbd.schoolofthought.codered.app.ui.emergency;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.tarmsbd.schoolofthought.codered.app.R;
import com.tarmsbd.schoolofthought.codered.app.ui.main.GoogleMapActivity;

public class BuyKitActivity extends AppCompatActivity {

    private WebView webview;
    ProgressBar progressBar;
    public String url="https://iamsabit.com/kit/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_kit);

        webview = (WebView)findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
        progressBar=(ProgressBar)findViewById(R.id.prg);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                setTitle("Downloading......");
            }
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view,url);
                progressBar.setVisibility(View.GONE);
                setTitle(view.getTitle());
            }

        });
        webview.loadUrl(url);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, GoogleMapActivity.class);
    }


}
