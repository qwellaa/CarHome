package com.lanou3g.carhome.search;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.networkrequest.DBTools;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

import java.util.ArrayList;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 *
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText et;
    private Button btnCancel, btnShare;
    private ListView lvSearch, lvHistory;
    private SearchAdapter adapter;
    private ImageButton iBtnClose;
    private WebView webView;
    private TextView tvRemove;
    private LinearLayout llHistory, llShare;
    private DBTools dbTools;
    private SearchHistoryAdapter historyAdapter;
    private List<SearchHistoryBean> historyArrayList;
    private String searchUrl;

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
        btnCancel = bindView(R.id.btn_search_cancel);
        et = bindView(R.id.et_search);
        lvSearch = bindView(R.id.lv_search_activity);
        iBtnClose = bindView(R.id.ibtn_close_search);
        webView = bindView(R.id.wv_search);
        lvHistory = bindView(R.id.lv_search_history_activity);
        tvRemove = bindView(R.id.tv_search_remove);
        llHistory = bindView(R.id.ll_search_activity);
        btnShare = bindView(R.id.btn_search_share);
        llShare = bindView(R.id.ll_share_search);
    }

    @Override
    protected void initData() {

        btnShare.setOnClickListener(this);
        tvRemove.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        iBtnClose.setOnClickListener(this);

        final Intent intent = getIntent();
        final String strHint = intent.getStringExtra("hint");
        et.setHint(strHint);

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    iBtnClose.setVisibility(View.GONE);
                    lvSearch.setVisibility(View.GONE);
                    webView.setVisibility(View.GONE);
                    llShare.setVisibility(View.GONE);
                    if (historyArrayList.size() > 0) {
                        llHistory.setVisibility(View.VISIBLE);
                        lvHistory.setVisibility(View.VISIBLE);
                    } else {
                        llHistory.setVisibility(View.GONE);
                        lvHistory.setVisibility(View.GONE);
                    }
                } else {

                    llHistory.setVisibility(View.GONE);
                    lvHistory.setVisibility(View.GONE);
                    iBtnClose.setVisibility(View.VISIBLE);
                    lvSearch.setVisibility(View.VISIBLE);
                }
                initSendInternet(s);
            }
        });

        adapter = new SearchAdapter(this);
        lvSearch.setAdapter(adapter);

        historyArrayList = new ArrayList<>();

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                webView.setVisibility(View.VISIBLE);
                llShare.setVisibility(View.VISIBLE);

                SearchBean bean = (SearchBean) parent.getItemAtPosition(position);
                String str= EncodeUtil.encode(bean.getResult().getWordlist().get(position).getName());
                searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword="+ str + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
                webView.loadUrl(searchUrl);
                webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);

                SearchHistoryBean historyBean = new SearchHistoryBean();
                historyBean.setName(bean.getResult().getWordlist().get(position).getName());
                historyBean.setUrl(searchUrl);
                dbTools.insertSearchHistory(historyBean);
                historyArrayList.add(historyBean);
                historyAdapter.setBeanList(historyArrayList);
            }
        });

        lvHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                llHistory.setVisibility(View.GONE);
                lvHistory.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                llShare.setVisibility(View.VISIBLE);

                SearchHistoryBean bean = (SearchHistoryBean) parent.getItemAtPosition(position);
                et.setText(bean.getName());
                searchUrl = bean.getUrl();
                webView.loadUrl(searchUrl);
                webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);
            }
        });


        historyAdapter = new SearchHistoryAdapter(this);

        dbTools = DBTools.getInstance();
        dbTools.getAllSearchHistory(new DBTools.QueryListener<SearchHistoryBean>() {
            @Override
            public void onQuery(List<SearchHistoryBean> beanArrayList) {
                historyArrayList = beanArrayList;
                historyAdapter.setBeanList(beanArrayList);
                Log.d("111", "1111");
                if (beanArrayList.size() > 0) {
                    llHistory.setVisibility(View.VISIBLE);
                    lvHistory.setVisibility(View.VISIBLE);
                } else {
                    llHistory.setVisibility(View.GONE);
                    lvHistory.setVisibility(View.GONE);
                }
            }
        });

        lvHistory.setAdapter(historyAdapter);
    }

    private void initSendInternet(Editable s) {
        if (!s.toString().equals("")) {
            String str =s.toString();
            String url = "http://mobilenc.app.autohome.com.cn/sou_v5.7.0/sou/suggestwords.ashx?pm=2&k=" + str+ "&t=4";
            GsonRequest<SearchBean> gsonRequest = new GsonRequest<SearchBean>(url,
                    SearchBean.class,
                    new Response.Listener<SearchBean>() {
                        @Override
                        public void onResponse(SearchBean response) {
                            adapter.setBean(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            VolleySingleton.getInstance().addRequest(gsonRequest);
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(searchUrl);
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
        oks.setSiteUrl(searchUrl);

        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_cancel:
                finish();
                break;
            case R.id.ibtn_close_search:
                et.setText(null);
                break;
            case R.id.tv_search_remove:
                final AlertDialog dialog = new AlertDialog.Builder(this).create();

                View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_remove, null);
                Button btnCancel = (Button) viewDialog.findViewById(R.id.btn_cancel_dialog);
                Button btnDetermine = (Button) viewDialog.findViewById(R.id.btn_determine_dialog);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                btnDetermine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dbTools.deleteAll(SearchHistoryBean.class);
                        historyArrayList.clear();
                        historyAdapter.setBeanList(historyArrayList);
                        dialog.cancel();
                        llHistory.setVisibility(View.GONE);
                        lvHistory.setVisibility(View.GONE);

                    }
                });

                dialog.setView(viewDialog);
                dialog.show();
                break;
            case R.id.btn_search_share:
                showShare();
                break;

        }
    }
}
