package com.lanou3g.carhome.recommend.tbrecommend;

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
import com.lanou3g.carhome.networkrequest.GsonRequest;
import com.lanou3g.carhome.networkrequest.URLValues;
import com.lanou3g.carhome.networkrequest.VolleySingleton;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;

/**
 *
 */
public class TabRecommendFragment extends BaseFragment{

    private PullToRefreshListView plvRecommend;
    private TabRecommendAdapter adapter;
    private Banner banner;
    
    @Override
    protected int setLayout() {
        return R.layout.fragment_tb_recommend;
    }

    @Override
    protected void initView() {
        plvRecommend = bindView(R.id.pLv_recommend);

        View bannerView = LayoutInflater.from(getContext()).inflate(R.layout.banner_recommend, null);
        banner = new Banner(context);
        banner = bindView(R.id.banner, bannerView);

        ListView listView = plvRecommend.getRefreshableView();
        listView.addHeaderView(bannerView);
    }

    @Override
    protected void initData() {
        plvRecommend.setMode(PullToRefreshBase.Mode.BOTH);
        plvRecommend.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            // 下拉刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                sendInterent();
                Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
            }

            // 上拉加载
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        adapter = new TabRecommendAdapter(context);
        plvRecommend.setAdapter(adapter);
        sendInterent();
    }

    private void sendInterent() {
        GsonRequest<TabRecommendBean> gsonRequest = new GsonRequest<TabRecommendBean>(URLValues.NEW_URL,
                TabRecommendBean.class,
                new Response.Listener<TabRecommendBean>() {
                    @Override
                    public void onResponse(TabRecommendBean response) {
                        adapter.setBean(response);
                        plvRecommend.onRefreshComplete();
                        ArrayList<String> images = new ArrayList<>();
                        for (int i = 0; i < response.getResult().getFocusimg().size(); i++) {
                            images.add(response.getResult().getFocusimg().get(i).getImgurl());
                        }

                        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        banner.setIndicatorGravity(BannerConfig.RIGHT);
                        banner.setImages(images);

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
