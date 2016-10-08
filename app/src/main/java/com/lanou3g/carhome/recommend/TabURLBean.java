package com.lanou3g.carhome.recommend;

import com.lanou3g.carhome.networkrequest.URLValues;

import java.util.ArrayList;

/**
 *
 */
public class TabURLBean {

    private TabURLBean(){

    }
    private static final ArrayList<String> URLArrayList = new ArrayList<>();

    public static final ArrayList<String> getUrls() {
        URLArrayList.add(URLValues.NEW_URL);
        URLArrayList.add(URLValues.U_MICRO_URL);
        URLArrayList.add(URLValues.LOBBYISTS_URL);
        URLArrayList.add(URLValues.VIDEO_URL);
        URLArrayList.add(URLValues.LETTERS_URL);
        URLArrayList.add(URLValues.THE_MARKET_URL);
        URLArrayList.add(URLValues.THE_NEWS_URL);
        URLArrayList.add(URLValues.REVIEW_URL);
        URLArrayList.add(URLValues.SHOPPERS_URL);
        URLArrayList.add(URLValues.THE_CAR_URL);
        URLArrayList.add(URLValues.TECHNOLOGY_URL);
        URLArrayList.add(URLValues.CULTURE_URL);
        URLArrayList.add(URLValues.A_MODIFIED_URL);
        return URLArrayList;
    }
}
