package com.lanou3g.carhome.recommend;

import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

/**
 *
 */
public class RecommendFragment extends BaseFragment{


    private PullToRefreshListView plvRecommend;
    private RecommendAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        plvRecommend = bindView(R.id.pLv_recommend);
    }

    @Override
    protected void initData() {
        plvRecommend.setMode(PullToRefreshBase.Mode.BOTH);


        plvRecommend.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            // 下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            // 上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        adapter = new RecommendAdapter(getContext());
        plvRecommend.setAdapter(adapter);
        sendInterent();
    }

    private void sendInterent() {
        GsonRequest<RecommendBean> gsonRequest = new GsonRequest<RecommendBean>(URLValues.NEW_URL,
                RecommendBean.class,
                new Response.Listener<RecommendBean>() {
                    @Override
                    public void onResponse(RecommendBean response) {
                        adapter.setBean(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
