package com.lanou3g.carhome.forum;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.forum.select.SelectFragment;
import com.lanou3g.carhome.forum.tbforum.TbForumFragment;

import java.util.ArrayList;


/**
 *
 */
public class ForumFragment extends BaseFragment {

    private TabLayout tbForum;
    private ViewPager vpForum;

    @Override
    protected int setLayout() {
        return R.layout.fragment_forum;
    }

    @Override
    protected void initView() {
        tbForum = bindView(R.id.tb_forum);
        vpForum = bindView(R.id.vp_forum);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new SelectFragment());
        fragments.add(new TbForumFragment());

        ArrayList<String> titles = new ArrayList<>();
        titles.add("精选");
        titles.add("论坛");

        ForumVpAdapter adapter = new ForumVpAdapter(getChildFragmentManager(), context);
        adapter.setFragments(fragments);
        adapter.setTitles(titles);
        vpForum.setAdapter(adapter);
        tbForum.setupWithViewPager(vpForum);

        // 更改tab 下滑线
        tbForum.setSelectedTabIndicatorColor(Color.BLACK);
        // 给tab文字 加选中颜色
        tbForum.setTabTextColors(Color.GRAY, Color.BLACK);
    }
}
