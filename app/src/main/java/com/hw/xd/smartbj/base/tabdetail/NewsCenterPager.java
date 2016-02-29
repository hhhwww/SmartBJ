package com.hw.xd.smartbj.base.tabdetail;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.hw.xd.smartbj.activity.MainAT;
import com.hw.xd.smartbj.base.MenuBase;
import com.hw.xd.smartbj.base.PagerBase;
import com.hw.xd.smartbj.base.menudetail.InteractMenuDetailPager;
import com.hw.xd.smartbj.base.menudetail.NewsMenuDetailPager;
import com.hw.xd.smartbj.base.menudetail.PhotoMenuDetailPager;
import com.hw.xd.smartbj.base.menudetail.TopicMenuDetailPager;
import com.hw.xd.smartbj.domain.NewsData;
import com.hw.xd.smartbj.fragment.LeftMenuFragment;
import com.hw.xd.smartbj.global.GlobalConstants;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhhhwei on 16/2/21.
 */
public class NewsCenterPager extends PagerBase {
    private List<MenuBase> mMenuDatas;
    private NewsData newsData;

    public NewsCenterPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initDatas() {
        super.initDatas();
        setSlidingMenuEnabled(true);

        getDatasFromServer();

        mMenuDatas = new ArrayList<>();

//        mMenuDatas.add(new NewsMenuDetailPager(mActivity, newsData.data.get(0).children));
//        mMenuDatas.add(new TopicMenuDetailPager(mActivity));
//        mMenuDatas.add(new PhotoMenuDetailPager(mActivity));
//        mMenuDatas.add(new InteractMenuDetailPager(mActivity));
    }

    private void getDatasFromServer() {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, GlobalConstants.CATEGORIES_URL, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.e("total", responseInfo.result);
                parseDataByGson(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.e("total", s);
            }
        });
    }

    private void parseDataByGson(String result) {
        Gson gson = new Gson();
        newsData = gson.fromJson(result, NewsData.class);

        //调用网络请求必须用真正的后面
        mMenuDatas.add(new NewsMenuDetailPager(mActivity, newsData.data.get(0).children));
        mMenuDatas.add(new TopicMenuDetailPager(mActivity));
        mMenuDatas.add(new PhotoMenuDetailPager(mActivity));
        mMenuDatas.add(new InteractMenuDetailPager(mActivity));

        //传递数据
        MainAT mainAT = (MainAT) mActivity;
        LeftMenuFragment leftFragment = mainAT.getLeftFragment();
        leftFragment.setMenuData(newsData);

        //当成功从网络上请求到数据后才可以加载第一次的界面，
        //所以把初始化第一次数据放到这里
        setCurrentPager(0);
    }

    public void setCurrentPager(int position) {
        flContentBase.removeAllViews();
        flContentBase.addView(mMenuDatas.get(position).viewBase);

        ArrayList<NewsData.NewsMenuData> data = newsData.data;
        tvTitleBase.setText(data.get(position).title);

        mMenuDatas.get(position).initDatas();
    }
}
