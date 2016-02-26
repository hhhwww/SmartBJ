package com.hw.xd.smartbj.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.hw.xd.smartbj.R;
import com.hw.xd.smartbj.fragment.ContentFragment;
import com.hw.xd.smartbj.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by hhhhwei on 16/2/18.
 */
public class MainAT extends SlidingFragmentActivity {
    private static final String LEFT_FRAGMENT = "left_fragment";
    private static final String CONTENT_FRAGMENT = "content_fragment";

    private FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBehindContentView(R.layout.sliding);

        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(1);
        slidingMenu.setBehindOffset(200);

        initFragments();
    }

    private void initFragments() {
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_left, new LeftMenuFragment(), LEFT_FRAGMENT);
        ft.replace(R.id.fl_content, new ContentFragment(), CONTENT_FRAGMENT);
        ft.commit();
    }

    public LeftMenuFragment getLeftFragment() {
        LeftMenuFragment leftMenuFragment = (LeftMenuFragment) fm.findFragmentByTag(LEFT_FRAGMENT);
        return leftMenuFragment;
    }

    public ContentFragment getContentFragment() {
        ContentFragment contentFragment = (ContentFragment) fm.findFragmentByTag(CONTENT_FRAGMENT);
        return contentFragment;
    }
}
