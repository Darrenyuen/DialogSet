package com.yuan.library.imagesdialog;

import android.content.Context;
import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * yuan
 * 2020/2/15
 **/
public class ImagesDialog extends ViewPager {
    public ImagesDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //再绘制一个关闭的图标
    }
}
