package com.kt.coffee.cat.utils;

import com.stx.xhb.androidx.entity.BaseBannerInfo;

import java.util.List;


/*
 * Home中的Home 轮播图数据实体类
 * */
public class HomeBannerEntity {


    /*private List<String> list;

    public List<String> getList() {
        return list;
    }*/


    private List<FeedBase> list;

    public List<FeedBase> getList() {
        return list;
    }

    public void setList(List<FeedBase> list) {
        this.list = list;
    }


    public static class FeedBase implements BaseBannerInfo {

        private String url;

        @Override
        public Object getXBannerUrl() {
            return url;
        }

        @Override
        public String getXBannerTitle() {
            return null;
        }
    }


    /*public List<FeedBase> getList() {
        return list;
    }

    public void setList(List<FeedBase> list) {
        this.list = list;
    }

    public static class FeedBase{
        private EntryBean entryBean;

        public EntryBean getEntryBean() {
            return entryBean;
        }

        public void setEntryBean(EntryBean entryBean) {
            this.entryBean = entryBean;
        }


        public static class EntryBean implements BaseBannerInfo {

            private String url;

            public String getUrl(){
                return url;
            }



            @Override
            public Object getXBannerUrl() {
                return url;
            }

            @Override
            public String getXBannerTitle() {
                return null;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

    }*/


}
