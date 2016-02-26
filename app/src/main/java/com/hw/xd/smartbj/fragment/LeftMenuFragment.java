package com.hw.xd.smartbj.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hw.xd.smartbj.R;
import com.hw.xd.smartbj.activity.MainAT;
import com.hw.xd.smartbj.base.tabdetail.NewsCenterPager;
import com.hw.xd.smartbj.domain.NewsData;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

/**
 * Created by hhhhwei on 16/2/19.
 */
public class LeftMenuFragment extends BaseFragment {
    private ListView lvLeftFragment;
    private ArrayList<NewsData.NewsMenuData> data;

    private int currentPosition;
    private MyAdapter myAdapter;

    @Override
    public View initViews() {
        View view = View.inflate(context, R.layout.fragment_left, null);

        lvLeftFragment = (ListView) view.findViewById(R.id.lv_left_fragment);

        return view;
    }

    @Override
    public void initDatas() {
        lvLeftFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentPosition = i;
                myAdapter.notifyDataSetChanged();
                setCurrentPager(i);
                toggle();
            }
        });
    }

    private void setCurrentPager(int i) {
        MainAT mainAT = (MainAT) getActivity();
        ContentFragment contentFragment = mainAT.getContentFragment();
        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
        newsCenterPager.setCurrentPager(i);
    }

    private void toggle() {
        SlidingMenu slidingMenu = ((MainAT) getActivity()).getSlidingMenu();
        slidingMenu.toggle();
    }

    public void setMenuData(NewsData newsData) {
        //数据源
        data = newsData.data;
        myAdapter = new MyAdapter();
        lvLeftFragment.setAdapter(myAdapter);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = View.inflate(getActivity(), R.layout.item_left_fragment, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_title);
            tv.setText(data.get(i).title);
            //nice try
            if (currentPosition == i)
                tv.setEnabled(true);
            else
                tv.setEnabled(false);

            return view;
        }
    }
}
