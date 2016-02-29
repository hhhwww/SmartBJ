package com.hw.xd.smartbj.base;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hw.xd.smartbj.R;
import com.hw.xd.smartbj.domain.NewsData;
import com.hw.xd.smartbj.domain.TabData;
import com.hw.xd.smartbj.global.GlobalConstants;
import com.hw.xd.smartbj.utils.ToastUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

/**
 * Created by hhhhwei on 16/2/26.
 */
public class TabDetailPager extends MenuBase {
    private NewsData.NewsTabData newsTabData;

    private TextView tvTitle;
    private CirclePageIndicator circlePageIndicator;

    private ViewPager vpTabDetail;

    private ListView lvNews;
    private List<TabData.TabNewsData> news;
    private MyViewAdapter myViewAdapter;

    private String url;
    private TabData tabData;

    public TabDetailPager(Activity activityBase, NewsData.NewsTabData newsTabData) {
        super(activityBase);
        this.newsTabData = newsTabData;
        url = GlobalConstants.SERVER_URL + newsTabData.url;
    }

    @Override
    public View initView() {
        View view = View.inflate(activityBase, R.layout.tab_detail_pager, null);
        View headerView = View.inflate(activityBase, R.layout.listview_head_tab_detail, null);
        vpTabDetail = (ViewPager) headerView.findViewById(R.id.vp_tab_detail_pager);
        tvTitle = (TextView) headerView.findViewById(R.id.tv_tab_detail_pager);
        circlePageIndicator = (CirclePageIndicator) headerView.findViewById(R.id.cpi_tab_detail_pager);

        lvNews = (ListView) view.findViewById(R.id.lv_tab_detail_pager);

        lvNews.addHeaderView(headerView);
        return view;
    }

    @Override
    public void initDatas() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                parseData(result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ToastUtils.showMessage(activityBase, s);
                e.printStackTrace();
            }
        });
    }

    private void parseData(String result) {
        Gson gson = new Gson();
        tabData = gson.fromJson(result, TabData.class);

        news = tabData.data.news;

        vpTabDetail.setAdapter(new MyPagerAdapter());
        circlePageIndicator.setViewPager(vpTabDetail);
        circlePageIndicator.setSnap(true);
        circlePageIndicator.onPageSelected(0);

        //初始化title
        tvTitle.setText(tabData.data.topnews.get(0).title);

        //初始化adapter
        lvNews.setAdapter(new MyViewAdapter());

        //很关键，但很稳健
        circlePageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title = tabData.data.topnews.get(position).title;
                tvTitle.setText(title);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyPagerAdapter extends PagerAdapter {

        private final BitmapUtils bitmapUtils;

        MyPagerAdapter() {
            bitmapUtils = new BitmapUtils(activityBase);
        }

        @Override
        public int getCount() {
            return tabData.data.topnews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(activityBase);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            bitmapUtils.display(imageView,
                    GlobalConstants.SERVER_URL + tabData.data.topnews.get(position).topimage.substring(25)
            );
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private class MyViewAdapter extends BaseAdapter {

        private final BitmapUtils bitmapUtils;

        public MyViewAdapter() {
            bitmapUtils = new BitmapUtils(activityBase);
        }

        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int i) {
            return news.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            MyViewHolder myViewHolder = null;
            if (view == null) {
                view = View.inflate(activityBase, R.layout.item_tab_detail_pager, null);
                myViewHolder = new MyViewHolder();
                myViewHolder.ivPic = (ImageView) view.findViewById(R.id.iv_item_tab);
                myViewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_item_title);
                myViewHolder.tvDate = (TextView) view.findViewById(R.id.tv_item_date);
                view.setTag(myViewHolder);
            } else
                myViewHolder = (MyViewHolder) view.getTag();

            TabData.TabNewsData item = (TabData.TabNewsData) getItem(i);

            bitmapUtils.display(myViewHolder.ivPic,
                    GlobalConstants.SERVER_URL + item.listimage.substring(25));
            myViewHolder.tvTitle.setText(item.title);
            myViewHolder.tvDate.setText(item.pubdate);

            return view;
        }

        private class MyViewHolder {
            public ImageView ivPic;
            public TextView tvTitle;
            public TextView tvDate;
        }
    }
}
