package com.hw.xd.smartbj.domain;

import java.util.List;

/**
 * Created by hhhhwei on 16/2/28.
 */
public class TabData {
    public int retcode;
    public TabDetail data;

    public class TabDetail {
        public String more;
        public String title;
        public List<TabNewsData> news;
        public List<TopNewsData> topnews;

        @Override
        public String toString() {
            return "TabDetail [title=" + title + ", news=" + news
                    + ", topnews=" + topnews + "]";
        }
    }

    public class TabNewsData {
        public String id;
        public String listimage;
        public String pubdate;
        public String title;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "TabNewsData [title=" + title + "]";
        }
    }

    public class TopNewsData {
        public String id;
        public String topimage;
        public String pubdate;
        public String title;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "TopNewsData [title=" + title + "]";
        }
    }

    @Override
    public String toString() {
        return "TabData [data=" + data + "]";
    }
}
