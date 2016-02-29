package com.hw.xd.smartbj.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hhhhwei on 16/2/28.
 */
public class ToastUtils {
    public static void showMessage(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
