package com.lanou3g.carhome.guidepage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 *
 */
public class GuidePageAdapter extends FragmentPagerAdapter{

    Context context;
    ArrayList<Fragment> fragments;

    public GuidePageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public GuidePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
