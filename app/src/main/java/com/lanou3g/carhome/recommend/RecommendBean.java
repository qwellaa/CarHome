package com.lanou3g.carhome.recommend;

/**
 *
 */
public class RecommendBean {
    private String title;
    private String replycount;
    private String time;
    private String smallpic;

    public RecommendBean(String title, String replycount, String time, String smallpic) {
        this.title = title;
        this.replycount = replycount;
        this.time = time;
        this.smallpic = smallpic;
    }

    public RecommendBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReplycount() {
        return replycount;
    }

    public void setReplycount(String replycount) {
        this.replycount = replycount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSmallpic() {
        return smallpic;
    }

    public void setSmallpic(String smallpic) {
        this.smallpic = smallpic;
    }
}
