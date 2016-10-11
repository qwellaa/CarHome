package com.lanou3g.carhome.search;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

/**
 *
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private EditText et;
    private Button btnCancel;
    private ListView lvSearch;
    private SearchAdapter adapter;
    private ImageButton iBtnClose;
    private WebView webView;

    @Override
    protected int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        btnCancel = bindView(R.id.btn_search_cancel);
        et = bindView(R.id.et_search);
        lvSearch = bindView(R.id.lv_search_activity);
        iBtnClose = bindView(R.id.ibtn_close_search);
        webView = bindView(R.id.wv_search);
    }

    @Override
    protected void initData() {
        btnCancel.setOnClickListener(this);
        iBtnClose.setOnClickListener(this);

        final Intent intent = getIntent();
        String strHint = intent.getStringExtra("hint");
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
                } else {
                    iBtnClose.setVisibility(View.VISIBLE);
                    lvSearch.setVisibility(View.VISIBLE);
                }
                initSendInternet(s);
            }
        });

        adapter = new SearchAdapter(this);
        lvSearch.setAdapter(adapter);

        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                webView.setVisibility(View.VISIBLE);
                SearchBean bean = (SearchBean) parent.getItemAtPosition(position);
                String str= EncodeUtil.encode(bean.getResult().getWordlist().get(position).getName());
                String searchUrl = "http://sou.m.autohome.com.cn/h5/1.1/search.html?type=0&keyword="+ str + "&night=0&bbsid=0&lng=121.550912&lat=38.889734&nettype=5&netprovider=0";
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_cancel:
                finish();
                break;
            case R.id.ibtn_close_search:
                et.setText(null);
                break;
        }
    }
}
