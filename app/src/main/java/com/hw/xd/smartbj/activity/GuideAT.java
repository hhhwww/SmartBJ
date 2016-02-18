package com.hw.xd.smartbj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hw.xd.smartbj.R;
import com.hw.xd.smartbj.utils.PrefUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hhhhwei on 16/2/17.
 */
public class GuideAT extends AppCompatActivity {
    private ViewPager vpGuide;
    private List<ImageView> mDatas;
    private MyPageAdapter myPageAdapter;
    private int[] mImages = new int[]{R.drawable.guide_1, R.drawable.guide_2,
            R.drawable.guide_3};

    private Button btSure;
    private View viewRedPoint;
    private LinearLayout llPointGroup;

    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        initViews();
        initDatas();
        setListeners();
    }

    private void initViews() {
        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        myPageAdapter = new MyPageAdapter();
        mDatas = new ArrayList<>();

        btSure = (Button) findViewById(R.id.bt_guide);
        viewRedPoint = findViewById(R.id.view_red_point);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
    }

    private void initDatas() {
        for (int i = 0; i < mImages.length; i++) {
            RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(rl);
//            imageView.setImageResource(mImages[i]);   src
            imageView.setBackgroundResource(mImages[i]);
            mDatas.add(imageView);
        }
        vpGuide.setAdapter(myPageAdapter);

        //调完layout的回调的方法
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
                llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void setListeners() {
        vpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //不断
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
                layoutParams.leftMargin = (int) (positionOffset * width + position * width);
                //刷新
                viewRedPoint.setLayoutParams(layoutParams);
                //动了起来
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mDatas.size() - 1)
                    btSure.setVisibility(View.VISIBLE);
                else
                    btSure.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefUtils.putBoolean(GuideAT.this, "is_user_guide_showed", true);
                startActivity(new Intent(GuideAT.this, MainAT.class));
                finish();
            }
        });
    }

    private class MyPageAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mDatas.get(position));
            return mDatas.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
