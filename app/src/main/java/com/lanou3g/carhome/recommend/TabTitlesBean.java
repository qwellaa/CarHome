package com.lanou3g.carhome.recommend;

import java.util.ArrayList;

/**
 *
 */
public class TabTitlesBean {

    public TabTitlesBean() {
    }

    private static final ArrayList<String> titles = new ArrayList<>();

    public static final ArrayList<String> getTitles() {
        titles.add("推荐");
        titles.add("优创+");
        titles.add("说客");
        titles.add("视频");
        titles.add("快报");
        titles.add("行情");
        titles.add("新闻");
        titles.add("评测");
        titles.add("导购");
        titles.add("用车");
        titles.add("技术");
        titles.add("文化");
        titles.add("改装");
        return titles;
    }
}
