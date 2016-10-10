package com.lanou3g.carhome.search;

import java.util.List;

/**
 *
 */
public class SearchBean {

    /**
     * returncode : 0
     * message :
     * result : {"pageindex":1,"pagesize":0,"pagecount":1,"rowcount":10,"wordlist":[{"id":0,"name":"雪佛兰"},{"id":3582,"name":"本田XR-V"},{"id":3934,"name":"雷克萨斯RX"},{"id":3839,"name":"绅宝X25"},{"id":448,"name":"轩逸"},{"id":564,"name":"逍客"},{"id":329,"name":"雪铁龙C3"},{"id":4043,"name":"雪铁龙C6"},{"id":3429,"name":"雪铁龙C3-XR"},{"id":2945,"name":"雪铁龙C4L"}]}
     */

    private int returncode;
    private String message;
    /**
     * pageindex : 1
     * pagesize : 0
     * pagecount : 1
     * rowcount : 10
     * wordlist : [{"id":0,"name":"雪佛兰"},{"id":3582,"name":"本田XR-V"},{"id":3934,"name":"雷克萨斯RX"},{"id":3839,"name":"绅宝X25"},{"id":448,"name":"轩逸"},{"id":564,"name":"逍客"},{"id":329,"name":"雪铁龙C3"},{"id":4043,"name":"雪铁龙C6"},{"id":3429,"name":"雪铁龙C3-XR"},{"id":2945,"name":"雪铁龙C4L"}]
     */

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
        private int pageindex;
        private int pagesize;
        private int pagecount;
        private int rowcount;
        /**
         * id : 0
         * name : 雪佛兰
         */

        private List<WordlistBean> wordlist;

        public int getPageindex() {
            return pageindex;
        }

        public void setPageindex(int pageindex) {
            this.pageindex = pageindex;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getPagecount() {
            return pagecount;
        }

        public void setPagecount(int pagecount) {
            this.pagecount = pagecount;
        }

        public int getRowcount() {
            return rowcount;
        }

        public void setRowcount(int rowcount) {
            this.rowcount = rowcount;
        }

        public List<WordlistBean> getWordlist() {
            return wordlist;
        }

        public void setWordlist(List<WordlistBean> wordlist) {
            this.wordlist = wordlist;
        }

        public static class WordlistBean {
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
