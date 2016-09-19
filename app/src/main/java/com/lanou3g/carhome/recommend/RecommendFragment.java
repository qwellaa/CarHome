package com.lanou3g.carhome.recommend;

import android.widget.ListView;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;

/**
 *
 */
public class RecommendFragment extends BaseFragment{

    private ListView lvRecommend;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {
        lvRecommend = bindView(R.id.lv_recommend);
    }

    @Override
    protected void initData() {
        
    }
}
