package com.hw.xd.smartbj.base.menudetail;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.hw.xd.smartbj.R;
import com.hw.xd.smartbj.activity.MainAT;
import com.hw.xd.smartbj.base.MenuBase;
import com.hw.xd.smartbj.base.TabDetailPager;
import com.hw.xd.smartbj.domain.NewsData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhhhwei on 16/2/23.
 */
public class NewsMenuDetailPager extends MenuBase {
    private ViewPager vpNews;
    private List<TabDetailPager> mDatas;
    private ArrayList<NewsData.NewsTabData> children;

    private TabPageIndicator tabPageIndicator;
    private ImageButton ibNextPage;

    public NewsMenuDetailPager(Activity activityBase, ArrayList<NewsData.NewsTabData> children) {
        super(activityBase);
        this.children = children;
    }

    @Override
    public View initView() {
        View view = View.inflate(activityBase, R.layout.news_menu_detail, null);
        vpNews = (ViewPager) view.findViewById(R.id.vp_news_menu_detail);
        tabPageIndicator = (TabPageIndicator) view.findViewById(R.id.tabpagerindicator);
        ibNextPage = (ImageButton) view.findViewById(R.id.ib_next_page);
        mDatas = new ArrayList<>();
        return view;
    }

    @Override
    public void initDatas() {
        for (int i = 0; i < children.size(); i++) {
            TabDetailPager tabDetailPager = new TabDetailPager(activityBase, children.get(i));
            mDatas.add(tabDetailPager);
        }
        vpNews.setAdapter(new MyBaseAdapter());
        tabPageIndicator.setViewPager(vpNews);

        ibNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentItem = vpNews.getCurrentItem();
                vpNews.setCurrentItem(++currentItem);
            }
        });

        tabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MainAT mainAT = (MainAT) activityBase;
                SlidingMenu slidingMenu = mainAT.getSlidingMenu();
                if (position == 0)
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                else
                    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyBaseAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager = mDatas.get(position);
            container.addView(tabDetailPager.viewBase);
            tabDetailPager.initDatas();
            return tabDetailPager.viewBase;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return children.get(position).title;
        }
    }
}
