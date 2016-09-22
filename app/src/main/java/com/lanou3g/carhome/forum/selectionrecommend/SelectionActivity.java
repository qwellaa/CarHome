package com.lanou3g.carhome.forum.selectionrecommend;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

/**
 *
 */
public class SelectionActivity extends BaseActivity{


    private TextView tvInTitle;
    private ImageButton ibtnBack;
    private SelectionAdapter adapter;
    private PullToRefreshListView plvSelection;

    @Override
    protected int setLayout() {
        return R.layout.activity_selection;
    }

    @Override
    protected void initView() {
        plvSelection = bindView(R.id.pLv_selection);
        tvInTitle = bindView(R.id.tv_title_custom_title_selection);
        ibtnBack = bindView(R.id.ibtn_back_custom_title_selection);

    }

    @Override
    protected void initData() {

        adapter = new SelectionAdapter(this);
        plvSelection.setAdapter(adapter);
        plvSelection.setMode(PullToRefreshBase.Mode.BOTH);
        plvSelection.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                selectionSendInterent();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        selectionSendInterent();
        initBackFinish();
    }

    private void selectionSendInterent() {
        Intent intent = getIntent();
        String strUrl = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");

        tvInTitle.setText(title);

        GsonRequest<SelectionBean> gsonRequest = new GsonRequest<SelectionBean>(strUrl,
                SelectionBean.class,
                new Response.Listener<SelectionBean>() {
                    @Override
                    public void onResponse(SelectionBean response) {
                        adapter.setBean(response);
                        plvSelection.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SelectionActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }


    public void initBackFinish() {
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
