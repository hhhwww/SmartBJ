package com.hw.xd.smartbj.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hw.xd.smartbj.R;
import com.hw.xd.smartbj.activity.MainAT;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by hhhhwei on 16/2/21.
 */
public class PagerBase {
    protected Activity mActivity;

    protected ImageButton ibMenuBase;
    protected TextView tvTitleBase;
    protected FrameLayout flContentBase;
    public View viewBase;

    public PagerBase(Activity mActivity) {
        this.mActivity = mActivity;
        initViews();
    }

    //不能允许被覆盖，因为父类已经抽取出来了共性,子类只需要针对性的实现即可.
    final public void initViews() {
        viewBase = View.inflate(mActivity, R.layout.base_pager, null);
        ibMenuBase = (ImageButton) viewBase.findViewById(R.id.ib_menu_base);
        tvTitleBase = (TextView) viewBase.findViewById(R.id.tv_title_base);
        flContentBase = (FrameLayout) viewBase.findViewById(R.id.fl_content_base);

        ibMenuBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });
    }

    private void toggle() {
        SlidingMenu slidingMenu = ((MainAT) mActivity).getSlidingMenu();
        slidingMenu.toggle();
    }

    public void initDatas() {

    }

    protected void setSlidingMenuEnabled(boolean isShow) {
        MainAT mainAT = (MainAT) mActivity;
        SlidingMenu slidingMenu = mainAT.getSlidingMenu();
        if (isShow)
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        else
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    }
}
