package com.lanou3g.carhome.findcar.usedcar;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.networkrequest.URLValues;

/**
 *
 */
public class UsedCarFragment extends BaseFragment{

    private WebView wv;

    @Override
    protected int setLayout() {
        return R.layout.fragment_findcar_used_car;
    }

    @Override
    protected void initView() {
        wv = bindView(R.id.wv_use_car);
    }

    @Override
    protected void initData() {
        wv.loadUrl(URLValues.USE_CAR_URL);
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
