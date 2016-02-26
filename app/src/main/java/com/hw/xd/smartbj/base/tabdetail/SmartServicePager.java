package com.hw.xd.smartbj.base.tabdetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.hw.xd.smartbj.base.PagerBase;

/**
 * Created by hhhhwei on 16/2/21.
 */
public class SmartServicePager extends PagerBase {
    public SmartServicePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initDatas() {
        super.initDatas();
        tvTitleBase.setText("生活");
        setSlidingMenuEnabled(true);

        TextView textView = new TextView(mActivity);
        textView.setText("智慧服务");
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        flContentBase.addView(textView);
    }
}
