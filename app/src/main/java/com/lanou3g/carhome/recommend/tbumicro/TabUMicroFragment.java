package com.lanou3g.carhome.recommend.tbumicro;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 *
 */
public class TabUMicroFragment extends BaseFragment{

    private PullToRefreshListView plvUm;
    private TabUMicroAdapter adapter;
    private Banner banner;

    @Override
    protected int setLayout() {
        return R.layout.fragment_u_micro;
    }

    @Override
    protected void initView() {
        plvUm = bindView(R.id.pLv_recommend_u_micro);

        View bannerView = LayoutInflater.from(getContext()).inflate(R.layout.banner_recommend, null);
        banner = new Banner(context);
        banner = bindView(R.id.banner, bannerView);

        ListView listView = plvUm.getRefreshableView();
        listView.addHeaderView(bannerView);
    }

    @Override
    protected void initData() {
        plvUm.setMode(PullToRefreshBase.Mode.BOTH);
        plvUm.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                initSendInterent();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });

        adapter = new TabUMicroAdapter(context);
        plvUm.setAdapter(adapter);
        initSendInterent();
    }

    private void initSendInterent() {
        GsonRequest<TabUMicroBean> gsonRequest = new GsonRequest<TabUMicroBean>(URLValues.U_MICRO_URL,
                TabUMicroBean.class,
                new Response.Listener<TabUMicroBean>() {
                    @Override
                    public void onResponse(TabUMicroBean response) {
                        adapter.setBean(response);
                        ArrayList<String> images = new ArrayList<>();
                        for (int i = 0; i < response.getResult().getFocusimgs().size(); i++) {
                            images.add(response.getResult().getFocusimgs().get(i).getImgurl());
                        }
                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.setIndicatorGravity(BannerConfig.RIGHT);
                        banner.setImages(images);

                        plvUm.onRefreshComplete();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);

    }
}
