package com.lanou3g.carhome.recommend.tbumicro;

import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;

/**
 *
 */
public class TabUMicroFragment extends BaseFragment{

    private PullToRefreshListView plvUm;

    @Override
    protected int setLayout() {
        return R.layout.fragment_u_micro;
    }

    @Override
    protected void initView() {
        plvUm = bindView(R.id.pLv_recommend_u_micro);
    }

    @Override
    protected void initData() {
        plvUm.setMode(PullToRefreshBase.Mode.BOTH);
        plvUm.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        initSendInterent();
    }

    private void initSendInterent() {
        GsonRequest<TabUMicroBean> gsonRequest = new GsonRequest<TabUMicroBean>(URLValues.U_Micro_URL,
                TabUMicroBean.class,
                new Response.Listener<TabUMicroBean>() {
                    @Override
                    public void onResponse(TabUMicroBean response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
