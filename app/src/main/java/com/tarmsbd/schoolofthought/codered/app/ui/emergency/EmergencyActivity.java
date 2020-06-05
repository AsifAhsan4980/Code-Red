package com.tarmsbd.schoolofthought.codered.app.ui.emergency;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.tarmsbd.schoolofthought.codered.app.R;
import com.tarmsbd.schoolofthought.codered.app.ui.main.GoogleMapActivity;

import java.io.File;


public class EmergencyActivity extends AppCompatActivity  {

    private WebView webview;
    ProgressBar progressBar;
    public String url="https://www.tidio.com/talk/ovdwkq6mexdtckajrwg0nbynhce8gbg2/";
    protected File extStorageAppBasePath;
    protected File extStorageAppCachePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        webview = (WebView)findViewById(R.id.webview);
        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        progressBar=(ProgressBar)findViewById(R.id.prg);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
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

        //ProgressDialog dialog = ProgressDialog.show(this, "Down Loading", "Please wait ...", true);


        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File externalStorageDir = Environment.getExternalStorageDirectory();
            if (externalStorageDir != null) {
                extStorageAppBasePath = new File(externalStorageDir.getAbsolutePath() +
                        File.separator + "Android" + File.separator + "data" +
                        File.separator + getPackageName());
            }

            if (extStorageAppBasePath != null) {
                extStorageAppCachePath = new File(extStorageAppBasePath.getAbsolutePath() +
                        File.separator + "cache");

                boolean isCachePathAvailable = true;

                if (!extStorageAppCachePath.exists()) {
                    isCachePathAvailable = extStorageAppCachePath.mkdirs();
                }

                if (!isCachePathAvailable) {
                    extStorageAppCachePath = null;
                }
            }
        }
    }

    @Override
    public File getCacheDir() {
        if (extStorageAppCachePath != null) {
            return extStorageAppCachePath;
        }
        else {
            return super.getCacheDir();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, GoogleMapActivity.class);
    }


}