package com.lanou3g.carhome.findcar.newcar;

/**
 * Created by dllo on 16/9/27.
 */

public class CarNameBean {
    private String name;
    private String pinYinName;
    private String imageUrl;

    public CarNameBean() {
        super();
    }

    public CarNameBean(String name, String pinYinName, String imageUrl) {
        super();
        this.name = name;
        this.pinYinName = pinYinName;
        this.imageUrl = imageUrl;
    }

    public CarNameBean(String name) {
        super();
        this.name = name;
    }

    public CarNameBean(String name, String pinYinName) {
        super();
        this.name = name;
        this.pinYinName = pinYinName;
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

    public String getPinYinName() {
        return pinYinName;
    }

    public void setPinYinName(String pinYinName) {
        this.pinYinName = pinYinName;
    }

}


