package com.yuan.library.utils;

import android.content.Context;

/**
 * yuan
 * 2020/2/12
 **/
public class LayoutUtil {
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
