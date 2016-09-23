package com.lanou3g.carhome.findcar;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 *
 */
public class FindCarAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private Context context;

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public FindCarAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
