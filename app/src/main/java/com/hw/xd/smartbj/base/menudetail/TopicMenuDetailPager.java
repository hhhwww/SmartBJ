package com.hw.xd.smartbj.base.menudetail;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.hw.xd.smartbj.base.MenuBase;

/**
 * Created by hhhhwei on 16/2/23.
 */
public class TopicMenuDetailPager extends MenuBase {
    public TopicMenuDetailPager(Activity activityBase) {
        super(activityBase);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(activityBase);
        textView.setText("2");
        textView.setTextColor(Color.RED);
        textView.setTextSize(25);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
