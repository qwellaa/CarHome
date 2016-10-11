package com.lanou3g.carhome.search;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 *
 */
public class SearchHistoryBean {

    // 指定自增, 每个对象需要有一个逐渐
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String name;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SearchHistoryBean(String name) {
        this.name = name;
    }

    public SearchHistoryBean() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
