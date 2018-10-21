package com.ancleron.rkappliances;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;


import com.ancleron.rkappliances.R;
import com.ancleron.rkappliances.utils.AppUtils;

public class MainActivity extends AppCompatActivity {
    WebView crm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crm = (WebView) findViewById(R.id.crm);

        crm.loadUrl("http://www.rkhomeappliances.in/rk_mobile/index.php");
        crm.setWebViewClient(new MyClient());
        crm.setWebChromeClient(new GoogleClient());
        WebSettings webSettings = crm.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        crm.getSettings().setJavaScriptCanOpenW [windowsAutomatically(true);
//        crm.clearCache(true);
        crm.clearHistory();

        crm.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        crm.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        crm.getSettings().setDomStorageEnabled(true);
        crm.getSettings().setDatabaseEnabled(true);
        crm.getSettings().setAppCacheEnabled(true);

     crm.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                try {
                    webView.stopLoading();
                } catch (Exception e) {
                }

                if (webView.canGoBack()) {
                    webView.goBack();
                }

                webView.loadUrl("about:blank");
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Check your internet connection and try again.");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });

                alertDialog.show();
                super.onReceivedError(webView, errorCode, description, failingUrl);
            }
        });



        crm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crm.loadUrl("http://www.rkhomeappliances.in/rk_mobile/index.php");
            }
        });


    }



    class MyClient extends WebViewClient
    {

        @Override
        public void onPageStarted(WebView view,String url,Bitmap favicon){
            super.onPageStarted(view,url,favicon);

        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String Url)
        {
            view.loadUrl(Url);
            return true;

        }
        @Override
        public void onPageFinished(WebView view,String url)
        {
            super.onPageFinished(view,url);

        }
    }
    class GoogleClient extends WebChromeClient
    {
        @Override
        public void onProgressChanged(WebView view,int newProgress)
        {
            super.onProgressChanged(view,newProgress);

        }
    }
    @Override
    public void onBackPressed() {
        if (crm.canGoBack())
            crm.goBack();
        else
            super.onBackPressed();

    }
}