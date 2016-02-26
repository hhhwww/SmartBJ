package com.hw.xd.smartbj.base;

import android.app.Activity;
import android.view.View;

/**
 * Created by hhhhwei on 16/2/23.
 */
public abstract class MenuBase {
    protected Activity activityBase;
    public View viewBase;

    public MenuBase(Activity activityBase) {
        this.activityBase = activityBase;
        viewBase = initView();
    }

    public abstract View initView();

    public void initDatas() {

    }
}
