package com.lanou3g.carhome.recommend;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.search.SearchActivity;

import java.util.ArrayList;

/**
 *
 */
public class RecommendFragment extends BaseFragment implements View.OnClickListener {




    private Button btnSearch;
    private TabLayout tbRecommend;
    private ViewPager vpRecommend;

    @Override
    protected int setLayout() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initView() {

        btnSearch = bindView(R.id.ibtn_search);
        tbRecommend = bindView(R.id.tb_recommend);
        vpRecommend = bindView(R.id.vp_recommend);

    }

    @Override
    protected void initData() {
        btnSearch.setOnClickListener(this);


        ArrayList<Fragment> fragments = TabFragmentBean.getFragments();
        ArrayList<String> titles = TabTitlesBean.getTitles();


        RecommendAdapter adapter = new RecommendAdapter(getChildFragmentManager(), context);
        adapter.setFragments(fragments);
        adapter.setTitles(titles);

        vpRecommend.setAdapter(adapter);
        tbRecommend.setupWithViewPager(vpRecommend);

        // 更改tab 下滑线
        tbRecommend.setSelectedTabIndicatorColor(Color.BLACK);
        // 给tab文字 加选中颜色
        tbRecommend.setTabTextColors(Color.GRAY, Color.BLACK);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ibtn_search:
                Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
                searchIntent.putExtra("hint", "搜索关键词");
                startActivity(searchIntent);
                break;
        }
    }
}
