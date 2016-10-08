package com.lanou3g.carhome.recommend;

import android.support.v4.app.Fragment;

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

    private static final ArrayList<Fragment> fragments = new ArrayList<>();

    public static final ArrayList<Fragment> getFragments() {
        fragments.add(new TabRecommendFragment());
        fragments.add(new TabUMicroFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabVideoFragment());
        fragments.add(new TabLobbyistsFragment());
        fragments.add(new TabVideoFragment());
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
