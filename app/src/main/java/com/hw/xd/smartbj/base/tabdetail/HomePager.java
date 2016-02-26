package com.hw.xd.smartbj.base.tabdetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hw.xd.smartbj.base.PagerBase;

/**
 * Created by hhhhwei on 16/2/21.
 */
public class HomePager extends PagerBase {
    public HomePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initDatas() {
        super.initDatas();
        tvTitleBase.setText("智慧北京");
        ibMenuBase.setVisibility(View.GONE);
        setSlidingMenuEnabled(false);

        TextView textView = new TextView(mActivity);
        textView.setText("首页");
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        flContentBase.addView(textView);
    }
}
