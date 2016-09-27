package com.lanou3g.carhome.welcome;

/**
 *
 */
public class WelcomeBean {

    /**
     * returncode : 0
     * message :
     * result : {"ishavead":1,"pvid":"46129f05-9329-4c82-8022-7dc237e1b9530","rdposturl":"http://rd.autohome.com.cn/adfront/realdeliver?pvid=46129f05-9329-4c82-8022-7dc237e1b9530","areaid":19637,"ad":{"imgad":{"imgurl":"http://img2.autoimg.cn/admdfs/g19/M04/2A/20/wKgFU1fd9y2AK6dwAAMCFa8tf-Q211.jpg","openurl":"http://c.autohome.com.cn/adfront/click?ah_mark=0B05956E54125E6CD43205781BC1D8F2EADBB45FB023AAA381E225D3C14832506AE8B00994FD49B6821E37CC892338BBA075CC363C3B175FFB9C277590C79F04B48DF558D601BF687A4915FF0295DA6C69007295A2F97EFD11271DA451DF6F1B&u=891F9629F439832C98AEA78E29BC15EB574FBD01A934FA4FEE1422926F7514440EE8D04E6B1C086468A308B3C170EF5BBA0972B4F367C1A63957CF00F0B18B08&imgtype=jpg"},"gifad":{"imgurl":"","openurl":"http://c.autohome.com.cn/adfront/click?ah_mark=0B05956E54125E6CD43205781BC1D8F2EADBB45FB023AAA381E225D3C14832506AE8B00994FD49B6821E37CC892338BBA075CC363C3B175FFB9C277590C79F04B48DF558D601BF687A4915FF0295DA6C69007295A2F97EFD11271DA451DF6F1B&u=&imgtype=gif"},"showtime":3,"skipbtn":1,"expiretime":"2016-10-04","splashid":19637,"materialid":"390905","thirdadurl":"http%3A%2F%2Fv.admaster.com.cn%2Fi%2Fa63668%2Cb970308%2Cc292%2Ci0%2Cm202%2Ch"}}
     */

    private int returncode;
    private String message;
    /**
     * ishavead : 1
     * pvid : 46129f05-9329-4c82-8022-7dc237e1b9530
     * rdposturl : http://rd.autohome.com.cn/adfront/realdeliver?pvid=46129f05-9329-4c82-8022-7dc237e1b9530
     * areaid : 19637
     * ad : {"imgad":{"imgurl":"http://img2.autoimg.cn/admdfs/g19/M04/2A/20/wKgFU1fd9y2AK6dwAAMCFa8tf-Q211.jpg","openurl":"http://c.autohome.com.cn/adfront/click?ah_mark=0B05956E54125E6CD43205781BC1D8F2EADBB45FB023AAA381E225D3C14832506AE8B00994FD49B6821E37CC892338BBA075CC363C3B175FFB9C277590C79F04B48DF558D601BF687A4915FF0295DA6C69007295A2F97EFD11271DA451DF6F1B&u=891F9629F439832C98AEA78E29BC15EB574FBD01A934FA4FEE1422926F7514440EE8D04E6B1C086468A308B3C170EF5BBA0972B4F367C1A63957CF00F0B18B08&imgtype=jpg"},"gifad":{"imgurl":"","openurl":"http://c.autohome.com.cn/adfront/click?ah_mark=0B05956E54125E6CD43205781BC1D8F2EADBB45FB023AAA381E225D3C14832506AE8B00994FD49B6821E37CC892338BBA075CC363C3B175FFB9C277590C79F04B48DF558D601BF687A4915FF0295DA6C69007295A2F97EFD11271DA451DF6F1B&u=&imgtype=gif"},"showtime":3,"skipbtn":1,"expiretime":"2016-10-04","splashid":19637,"materialid":"390905","thirdadurl":"http%3A%2F%2Fv.admaster.com.cn%2Fi%2Fa63668%2Cb970308%2Cc292%2Ci0%2Cm202%2Ch"}
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
        private int ishavead;
        private String pvid;
        private String rdposturl;
        private int areaid;
        /**
         * imgad : {"imgurl":"http://img2.autoimg.cn/admdfs/g19/M04/2A/20/wKgFU1fd9y2AK6dwAAMCFa8tf-Q211.jpg","openurl":"http://c.autohome.com.cn/adfront/click?ah_mark=0B05956E54125E6CD43205781BC1D8F2EADBB45FB023AAA381E225D3C14832506AE8B00994FD49B6821E37CC892338BBA075CC363C3B175FFB9C277590C79F04B48DF558D601BF687A4915FF0295DA6C69007295A2F97EFD11271DA451DF6F1B&u=891F9629F439832C98AEA78E29BC15EB574FBD01A934FA4FEE1422926F7514440EE8D04E6B1C086468A308B3C170EF5BBA0972B4F367C1A63957CF00F0B18B08&imgtype=jpg"}
         * gifad : {"imgurl":"","openurl":"http://c.autohome.com.cn/adfront/click?ah_mark=0B05956E54125E6CD43205781BC1D8F2EADBB45FB023AAA381E225D3C14832506AE8B00994FD49B6821E37CC892338BBA075CC363C3B175FFB9C277590C79F04B48DF558D601BF687A4915FF0295DA6C69007295A2F97EFD11271DA451DF6F1B&u=&imgtype=gif"}
         * showtime : 3
         * skipbtn : 1
         * expiretime : 2016-10-04
         * splashid : 19637
         * materialid : 390905
         * thirdadurl : http%3A%2F%2Fv.admaster.com.cn%2Fi%2Fa63668%2Cb970308%2Cc292%2Ci0%2Cm202%2Ch
         */

        private AdBean ad;

        public int getIshavead() {
            return ishavead;
        }

        public void setIshavead(int ishavead) {
            this.ishavead = ishavead;
        }

        public String getPvid() {
            return pvid;
        }

        public void setPvid(String pvid) {
            this.pvid = pvid;
        }

        public String getRdposturl() {
            return rdposturl;
        }

        public void setRdposturl(String rdposturl) {
            this.rdposturl = rdposturl;
        }

        public int getAreaid() {
            return areaid;
        }

        public void setAreaid(int areaid) {
            this.areaid = areaid;
        }

        public AdBean getAd() {
            return ad;
        }

        public void setAd(AdBean ad) {
            this.ad = ad;
        }

        public static class AdBean {
            /**
             * imgurl : http://img2.autoimg.cn/admdfs/g19/M04/2A/20/wKgFU1fd9y2AK6dwAAMCFa8tf-Q211.jpg
             * openurl : http://c.autohome.com.cn/adfront/click?ah_mark=0B05956E54125E6CD43205781BC1D8F2EADBB45FB023AAA381E225D3C14832506AE8B00994FD49B6821E37CC892338BBA075CC363C3B175FFB9C277590C79F04B48DF558D601BF687A4915FF0295DA6C69007295A2F97EFD11271DA451DF6F1B&u=891F9629F439832C98AEA78E29BC15EB574FBD01A934FA4FEE1422926F7514440EE8D04E6B1C086468A308B3C170EF5BBA0972B4F367C1A63957CF00F0B18B08&imgtype=jpg
             */

            private ImgadBean imgad;
            /**
             * imgurl :
             * openurl : http://c.autohome.com.cn/adfront/click?ah_mark=0B05956E54125E6CD43205781BC1D8F2EADBB45FB023AAA381E225D3C14832506AE8B00994FD49B6821E37CC892338BBA075CC363C3B175FFB9C277590C79F04B48DF558D601BF687A4915FF0295DA6C69007295A2F97EFD11271DA451DF6F1B&u=&imgtype=gif
             */

            private GifadBean gifad;
            private int showtime;
            private int skipbtn;
            private String expiretime;
            private int splashid;
            private String materialid;
            private String thirdadurl;

            public ImgadBean getImgad() {
                return imgad;
            }

            public void setImgad(ImgadBean imgad) {
                this.imgad = imgad;
            }

            public GifadBean getGifad() {
                return gifad;
            }

            public void setGifad(GifadBean gifad) {
                this.gifad = gifad;
            }

            public int getShowtime() {
                return showtime;
            }

            public void setShowtime(int showtime) {
                this.showtime = showtime;
            }

            public int getSkipbtn() {
                return skipbtn;
            }

            public void setSkipbtn(int skipbtn) {
                this.skipbtn = skipbtn;
            }

            public String getExpiretime() {
                return expiretime;
            }

            public void setExpiretime(String expiretime) {
                this.expiretime = expiretime;
            }

            public int getSplashid() {
                return splashid;
            }

            public void setSplashid(int splashid) {
                this.splashid = splashid;
            }

            public String getMaterialid() {
                return materialid;
            }

            public void setMaterialid(String materialid) {
                this.materialid = materialid;
            }

            public String getThirdadurl() {
                return thirdadurl;
            }

            public void setThirdadurl(String thirdadurl) {
                this.thirdadurl = thirdadurl;
            }

            public static class ImgadBean {
                private String imgurl;
                private String openurl;

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public String getOpenurl() {
                    return openurl;
                }

                public void setOpenurl(String openurl) {
                    this.openurl = openurl;
                }
            }

            public static class GifadBean {
                private String imgurl;
                private String openurl;

                public String getImgurl() {
                    return imgurl;
                }

                public void setImgurl(String imgurl) {
                    this.imgurl = imgurl;
                }

                public String getOpenurl() {
                    return openurl;
                }

                public void setOpenurl(String openurl) {
                    this.openurl = openurl;
                }
            }
        }
    }
}
