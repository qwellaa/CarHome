package com.lanou3g.carhome.recommend.tabmore;

/**
 *
 */
public class MoreBean {

    private String imageUrl;
    private String name;

    public MoreBean(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public MoreBean() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
