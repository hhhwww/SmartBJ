package com.hw.xd.smartbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by hhhhwei on 16/2/29.
 */
public class TabDetailViewPager extends ViewPager {
    private int startX;
    private int startY;

    public TabDetailViewPager(Context context) {
        super(context);
    }

    public TabDetailViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);

                startX = (int) ev.getRawX();
                startY = (int) ev.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                int endX = (int) ev.getRawX();
                int endY = (int) ev.getRawY();

                if (Math.abs(endX - startX) > Math.abs(endY - startY)) {//左右滑动
                    if (endX - startX > 0) {//右
                        if (getCurrentItem() == 0)
                            getParent().requestDisallowInterceptTouchEvent(false);
                    } else {//左
                        if (getCurrentItem() == getAdapter().getCount() - 1)
                            getParent().requestDisallowInterceptTouchEvent(false);
                    }
                } else {

                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
