package com.lanou3g.carhome.findcar;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseFragment;
import com.lanou3g.carhome.findcar.newcar.NewCarFragment;
import com.lanou3g.carhome.findcar.usedcar.UsedCarFragment;
import com.lanou3g.carhome.search.SearchActivity;

import java.util.ArrayList;

/**
 *
 */
public class FindCarFragment extends BaseFragment{

    private TabLayout tbFindCar;
    private ViewPager vpFindCar;
    private ImageButton iBtnSearch;

    @Override
    protected int setLayout() {
        return R.layout.fragment_findcar;
    }

    @Override
    protected void initView() {
        tbFindCar = bindView(R.id.tb_find_car);
        vpFindCar = bindView(R.id.vp_find_car);
        iBtnSearch = bindView(R.id.btn_search_find_car);
    }

    @Override
    protected void initData() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new NewCarFragment());
        fragments.add(new UsedCarFragment());
        ArrayList<String> titles = new ArrayList<>();
        titles.add("新车");
        titles.add("二手车");

        FindCarAdapter adapter = new FindCarAdapter(getChildFragmentManager(), context);
        adapter.setFragments(fragments);
        adapter.setTitles(titles);

        vpFindCar.setAdapter(adapter);
        tbFindCar.setupWithViewPager(vpFindCar);

        // 更改tab 下滑线
        tbFindCar.setSelectedTabIndicatorColor(Color.BLACK);
        // 给tab文字 加选中颜色
        tbFindCar.setTabTextColors(Color.GRAY, Color.BLACK);

        iBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getActivity(), SearchActivity.class);
                searchIntent.putExtra("hint", "搜索车系");
                startActivity(searchIntent);
            }
        });

    }

}
