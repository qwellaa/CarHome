package com.lanou3g.carhome.recommend;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lanou3g.carhome.recommend.tblobbyists.TabLobbyistsFragment;

import java.util.ArrayList;

/**
 *
 */
public class RecommendAdapter extends FragmentPagerAdapter{

    ArrayList<Fragment> fragments;
    Context context;
    ArrayList<String> titles;

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public RecommendAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {

        if (0 == position || 1 == position || 3 == position || 5 == position) {
            return fragments.get(position);
        } else {
            TabLobbyistsFragment fragment = new TabLobbyistsFragment();
            return fragment.newInstance(position);
        }
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
