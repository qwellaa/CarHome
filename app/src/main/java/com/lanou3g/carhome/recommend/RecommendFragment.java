package com.lanou3g.carhome.recommend;

import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;

/**
 *
 */
public class RecommendFragment extends BaseFragment{


    private PullToRefreshListView plvRecommend;

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
    }
}
