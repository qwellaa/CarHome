package com.lanou3g.carhome.recommend.tblobbyists;

import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;

/**
 *
 */
public class TabLobbyistsFragment extends BaseFragment{

    private PullToRefreshListView plvlobbyists;

    @Override
    protected int setLayout() {
        return R.layout.fragment_lobbyists;
    }

    @Override
    protected void initView() {
        plvlobbyists = bindView(R.id.pLv_recommend_lobbyists);
    }

    @Override
    protected void initData() {
        plvlobbyists.setMode(PullToRefreshBase.Mode.BOTH);
        plvlobbyists.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }
}
