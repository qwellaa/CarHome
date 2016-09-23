package com.lanou3g.carhome.discover;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.discover.goodslist.GoodsListAdapter;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;

/**
 *
 */
public class DiscoverFragment extends BaseFragment{

    private PullToRefreshListView plvDiscover;
    private GoodsListAdapter goodsListAdapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView() {
        plvDiscover = bindView(R.id.pLv_discover);

        View viewForMe = LayoutInflater.from(context).inflate(R.layout.headview_for_me_recommend, null);

    }

    @Override
    protected void initData() {
        plvDiscover.setMode(PullToRefreshBase.Mode.BOTH);

        plvDiscover.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initSendInterent();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        goodsListAdapter = new GoodsListAdapter(context);

        initSendInterent();

        plvDiscover.setAdapter(goodsListAdapter);
    }

    private void initSendInterent() {
        GsonRequest<DiscoverBean> gsonRequest = new GsonRequest<DiscoverBean>(URLValues.GOODS_LIST_URL,
                DiscoverBean.class,
                new Response.Listener<DiscoverBean>() {
                    @Override
                    public void onResponse(DiscoverBean response) {
                        goodsListAdapter.setBean(response);

                        plvDiscover.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "网络请求失败", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
