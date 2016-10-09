package com.lanou3g.carhome.networkrequest;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;

/**
 *
 */
public class WebViewActivity extends BaseActivity{

    private WebView wv;

    @Override
    protected int setLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        wv = bindView(R.id.wv);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("urlWv");
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
    }
}
