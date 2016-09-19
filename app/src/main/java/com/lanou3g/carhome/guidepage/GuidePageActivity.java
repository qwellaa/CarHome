package com.lanou3g.carhome.guidepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.carhome.R;
import com.lanou3g.carhome.baseclass.BaseActivity;
import com.lanou3g.carhome.welcome.WelcomeActivity;

import java.util.ArrayList;

/**
 *
 */
public class GuidePageActivity extends BaseActivity{

    private ViewPager vp;

    @Override
    protected int setLayout() {
        SharedPreferences sp = getSharedPreferences("launch", MODE_PRIVATE);
        SharedPreferences.Editor spEt = sp.edit();
        spEt.commit();

        Boolean isFirst = sp.getBoolean("isFirst", false);

        if (isFirst == true) {
            Intent intent = new Intent(GuidePageActivity.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
        return R.layout.activity_guidepage;
    }

    @Override
    protected void initView() {
        vp = bindView(R.id.vp_guide_page);
    }

    @Override
    protected void initData() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FirstGuidePageFragment());
        fragments.add(new SecondGuidePageFragment());
        fragments.add(new ThirdGuidePageFragment());
        fragments.add(new FourthGuidePageFragment());

        GuidePageAdapter adapter = new GuidePageAdapter(getSupportFragmentManager(), this);
        adapter.setFragments(fragments);
        vp.setAdapter(adapter);
    }
}
