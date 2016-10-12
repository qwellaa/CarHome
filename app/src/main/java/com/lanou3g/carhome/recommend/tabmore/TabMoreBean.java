package com.lanou3g.carhome.recommend.tabmore;

import java.util.List;

/**
 *
 */
public class TabMoreBean {

    /**
     * returncode : 0
     * message : ok
     * result : {"metalist":[{"key":"newstype","timestamp":636099619431200100,"list":[{"name":"优创+","value":"118","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/youchuang3.png?636099619431200120"},{"name":"说客","value":"109","type":"4","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/shuoke3.png?636099619431200120"},{"name":"视频","value":"0","type":"2","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/shipin3.png?636099619431200120"},{"name":"快报","value":"117","type":"5","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/kuaibao3.png?636099619431200120"},{"name":"游记","value":"100","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/youji3.png?636099619431200120"},{"name":"行情","value":"2","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/hangqing3.png?636099619431200120"},{"name":"新闻","value":"1","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/xinwen3.png?636099619431200120"},{"name":"评测","value":"3","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/pingce3.png?636099619431200120"},{"name":"导购","value":"60","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/daogou3.png?636099619431200120"},{"name":"用车","value":"82","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/yongche3.png?636099619431200120"},{"name":"技术","value":"102","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/jishu3.png?636099619431200120"},{"name":"文化","value":"97","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/wenhua3.png?636099619431200120"},{"name":"改装","value":"107","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/gaizhuang3.png?636099619431200120"}]},{"key":"videotype","timestamp":636099619431200100,"list":[{"name":"全部","value":"0","type":"1","iconurl":""},{"name":"原创","value":"23","type":"1","iconurl":""},{"name":"功能演示","value":"21","type":"1","iconurl":""},{"name":"改装/性能","value":"28","type":"2","iconurl":""},{"name":"事故/车祸","value":"3","type":"1","iconurl":""},{"name":"新车","value":"5","type":"1","iconurl":""},{"name":"越野","value":"16","type":"1","iconurl":""},{"name":"正能量","value":"25","type":"1","iconurl":""},{"name":"用车养车","value":"29","type":"1","iconurl":""},{"name":"美女","value":"2","type":"1","iconurl":""},{"name":"摩托车","value":"19","type":"1","iconurl":""},{"name":"聊车评车","value":"1","type":"1","iconurl":""},{"name":"街拍","value":"13","type":"1","iconurl":""},{"name":"碰撞测试","value":"17","type":"1","iconurl":""},{"name":"广告","value":"11","type":"1","iconurl":""},{"name":"游记","value":"7","type":"1","iconurl":""},{"name":"车展","value":"24","type":"1","iconurl":""},{"name":"电动车","value":"18","type":"1","iconurl":""},{"name":"二手车","value":"30","type":"1","iconurl":""},{"name":"其他","value":"22","type":"1","iconurl":""}]}]}
     */

    private int returncode;
    private String message;
    private ResultBean result;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * key : newstype
         * timestamp : 636099619431200100
         * list : [{"name":"优创+","value":"118","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/youchuang3.png?636099619431200120"},{"name":"说客","value":"109","type":"4","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/shuoke3.png?636099619431200120"},{"name":"视频","value":"0","type":"2","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/shipin3.png?636099619431200120"},{"name":"快报","value":"117","type":"5","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/kuaibao3.png?636099619431200120"},{"name":"游记","value":"100","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/youji3.png?636099619431200120"},{"name":"行情","value":"2","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/hangqing3.png?636099619431200120"},{"name":"新闻","value":"1","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/xinwen3.png?636099619431200120"},{"name":"评测","value":"3","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/pingce3.png?636099619431200120"},{"name":"导购","value":"60","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/daogou3.png?636099619431200120"},{"name":"用车","value":"82","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/yongche3.png?636099619431200120"},{"name":"技术","value":"102","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/jishu3.png?636099619431200120"},{"name":"文化","value":"97","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/wenhua3.png?636099619431200120"},{"name":"改装","value":"107","type":"1","iconurl":"http://x.autoimg.cn/app/image/metadataicon/newstype/gaizhuang3.png?636099619431200120"}]
         */

        private List<MetalistBean> metalist;

        public List<MetalistBean> getMetalist() {
            return metalist;
        }

        public void setMetalist(List<MetalistBean> metalist) {
            this.metalist = metalist;
        }

        public static class MetalistBean {
            private String key;
            private long timestamp;
            /**
             * name : 优创+
             * value : 118
             * type : 1
             * iconurl : http://x.autoimg.cn/app/image/metadataicon/newstype/youchuang3.png?636099619431200120
             */

            private List<ListBean> list;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(long timestamp) {
                this.timestamp = timestamp;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                private String name;
                private String value;
                private String type;
                private String iconurl;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getIconurl() {
                    return iconurl;
                }

                public void setIconurl(String iconurl) {
                    this.iconurl = iconurl;
                }
            }
        }
    }
}
