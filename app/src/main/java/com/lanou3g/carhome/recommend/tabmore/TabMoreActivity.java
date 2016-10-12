package com.lanou3g.carhome.recommend.tabmore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

/**
 *
 */
public class TabMoreActivity extends BaseActivity{

    private ImageButton ibtnBack;
    private RecyclerView rvMore;
    private TabMoreAdapter moreAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_recommend_more;
    }

    @Override
    protected void initView() {
        ibtnBack = bindView(R.id.ibtn_back_recommend_more);
        rvMore = bindView(R.id.rv_recommend_more);
    }

    @Override
    protected void initData() {
        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        moreAdapter = new TabMoreAdapter(this);
        rvMore.setAdapter(moreAdapter);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rvMore.setLayoutManager(manager);

        initSendInterent();
    }

    private void initSendInterent() {
        GsonRequest<TabMoreBean> gsonRequest = new GsonRequest<TabMoreBean>(URLValues.TAB_ALL_URL,
                TabMoreBean.class,
                new Response.Listener<TabMoreBean>() {
                    @Override
                    public void onResponse(TabMoreBean response) {
                        moreAdapter.setBean(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
