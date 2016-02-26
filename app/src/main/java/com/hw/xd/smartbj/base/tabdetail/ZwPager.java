package com.hw.xd.smartbj.base.tabdetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.hw.xd.smartbj.base.PagerBase;

/**
 * Created by hhhhwei on 16/2/21.
 */
public class ZwPager extends PagerBase {
    public ZwPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initDatas() {
        super.initDatas();
        tvTitleBase.setText("人口管理");
        setSlidingMenuEnabled(true);

        TextView textView = new TextView(mActivity);
        textView.setText("政务");
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        flContentBase.addView(textView);
    }
}
