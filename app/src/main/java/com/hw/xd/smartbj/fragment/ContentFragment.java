package com.hw.xd.smartbj.fragment;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.hw.xd.smartbj.R;
import com.hw.xd.smartbj.base.PagerBase;
import com.hw.xd.smartbj.base.tabdetail.HomePager;
import com.hw.xd.smartbj.base.tabdetail.NewsCenterPager;
import com.hw.xd.smartbj.base.tabdetail.SettingPager;
import com.hw.xd.smartbj.base.tabdetail.SmartServicePager;
import com.hw.xd.smartbj.base.tabdetail.ZwPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhhhwei on 16/2/19.
 */
public class ContentFragment extends BaseFragment {
    @ViewInject(R.id.rg_group)
    private RadioGroup rgGroup;
    @ViewInject(R.id.vp_content)
    private ViewPager vpContent;

    private List<PagerBase> mDatas;

    @Override
    public View initViews() {
        View view = View.inflate(context, R.layout.fragment_content, null);
        mDatas = new ArrayList<>();
        ViewUtils.inject(this, view);
        return view;
    }

    @Override
    public void initDatas() {
//        //第一次初始化
        rgGroup.check(R.id.rb_home);
//        vpContent.setCurrentItem(0);

        Activity activity = (Activity) context;
        mDatas.add(new HomePager(activity));
        mDatas.add(new NewsCenterPager(activity));
        mDatas.add(new SmartServicePager(activity));
        mDatas.add(new ZwPager(activity));
        mDatas.add(new SettingPager(activity));

        vpContent.setAdapter(new MyPagerAdapter());

        setListeners();
    }

    private void setListeners() {
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        vpContent.setCurrentItem(0, false);
                        break;

                    case R.id.rb_news:
                        vpContent.setCurrentItem(1, false);
                        break;

                    case R.id.rb_smart:
                        vpContent.setCurrentItem(2, false);
                        break;

                    case R.id.rb_zw:
                        vpContent.setCurrentItem(3, false);
                        break;

                    case R.id.rb_setting:
                        vpContent.setCurrentItem(4, false);
                        break;
                }
            }
        });

        vpContent.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //在这进行加载数据,对各个被抽离出来共性的子类进行特别化处理
            @Override
            public void onPageSelected(int position) {
                mDatas.get(position).initDatas();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mDatas.get(0).initDatas();
    }

    //谁创造,谁贡献.
    public NewsCenterPager getNewsCenterPager() {
        NewsCenterPager newsCenterPager = (NewsCenterPager) mDatas.get(1);
        return newsCenterPager;
    }

    private class MyPagerAdapter extends PagerAdapter {
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
            container.addView(mDatas.get(position).viewBase);
            return mDatas.get(position).viewBase;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
