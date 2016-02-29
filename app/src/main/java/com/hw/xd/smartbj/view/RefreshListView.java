package com.hw.xd.smartbj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

import com.hw.xd.smartbj.R;

/**
 * Created by hhhhwei on 16/2/29.
 */
public class RefreshListView extends ListView {
    public RefreshListView(Context context) {
        super(context);
        initHeaderView();
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initHeaderView();
    }

    private void initHeaderView() {
        View view = View.inflate(getContext(), R.layout.refresh_header, null);
        this.addHeaderView(view);

        //先手动去测量完毕
        view.measure(0, 0);
        int measuredHeight = view.getMeasuredHeight();

        view.setPadding(0, -measuredHeight, 0, 0);
    }
}
