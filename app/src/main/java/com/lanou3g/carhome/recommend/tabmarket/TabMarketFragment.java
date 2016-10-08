package com.lanou3g.carhome.recommend.tabmarket;

import android.widget.ListView;
import android.widget.TextView;

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
public class TabMarketFragment extends BaseFragment{

    private PullToRefreshListView plvMarket;
    private TextView tvAddress;
    private TabMarketAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.fragment_market;
    }

    @Override
    protected void initView() {
        tvAddress = bindView(R.id.tv_address_market);
        plvMarket = bindView(R.id.pLv_recommend_market);
    }

    @Override
    protected void initData() {
        plvMarket.setMode(PullToRefreshBase.Mode.BOTH);
        plvMarket.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initSendInterent();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        adapter = new TabMarketAdapter(context);
        plvMarket.setAdapter(adapter);
        initSendInterent();
    }

    private void initSendInterent() {
        GsonRequest<TabMarketBean> gsonRequest = new GsonRequest<TabMarketBean>(URLValues.THE_MARKET_URL,
                TabMarketBean.class,
                new Response.Listener<TabMarketBean>() {
                    @Override
                    public void onResponse(TabMarketBean response) {
                        adapter.setBean(response);

                        plvMarket.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
