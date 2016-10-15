package com.lanou3g.carhome.networkrequest;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 *
 */
public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    private WebView wv;
    private Button btnShare;
    private String url;
    private LinearLayout llShare;

    @Override
    protected int setLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
        wv = bindView(R.id.wv);
        btnShare = bindView(R.id.btn_webview_share);
        llShare = bindView(R.id.ll_web_share);
    }

    @Override
    protected void initData() {

        btnShare.setOnClickListener(this);

        Intent intent = getIntent();
        url = intent.getStringExtra("urlWv");
        Boolean isAccordingShare = intent.getBooleanExtra("isAccordingShare", false);
        if (isAccordingShare) {
            llShare.setVisibility(View.VISIBLE);
        } else {
            llShare.setVisibility(View.GONE);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("图片网址");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_webview_share:
                showShare();
                break;
        }
    }
}
