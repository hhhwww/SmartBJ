package com.hw.xd.smartbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by hhhhwei on 16/2/22.
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //返回false,去掉ViewPager左右滑的功能
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    //返回false,不去拦截子控件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
