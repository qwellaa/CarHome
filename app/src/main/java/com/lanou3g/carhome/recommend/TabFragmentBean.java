package com.lanou3g.carhome.recommend;

import android.support.v4.app.Fragment;

import com.lanou3g.carhome.recommend.tabmarket.TabMarketFragment;
import com.lanou3g.carhome.recommend.tblobbyists.TabLobbyistsFragment;
import com.lanou3g.carhome.recommend.tbrecommend.TabRecommendFragment;
import com.lanou3g.carhome.recommend.tbumicro.TabUMicroFragment;
import com.lanou3g.carhome.recommend.tbvideo.TabVideoFragment;

import java.util.ArrayList;

/**
 *
 */
public class TabFragmentBean {


    public TabFragmentBean() {
    }

    private  ArrayList<Fragment> fragments = new ArrayList<>();

    public ArrayList<Fragment> getFragments() {
        fragments.add(new TabRecommendFragment());
        fragments.add(new TabUMicroFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabVideoFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabMarketFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabLobbyistsFragment());
        return fragments;
    }
}
