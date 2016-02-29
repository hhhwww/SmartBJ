package com.hw.xd.smartbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by hhhhwei on 16/2/27.
 */
public class HViewPager extends ViewPager {
    public HViewPager(Context context) {
        super(context);
    }

    public HViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentItem() != 0)
            getParent().requestDisallowInterceptTouchEvent(true);
        else
            getParent().requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);
    }
}
