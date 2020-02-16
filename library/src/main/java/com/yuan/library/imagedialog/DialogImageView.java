package com.yuan.library.imagedialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.yuan.library.R;
import com.yuan.library.utils.ConvertUtil;

/**
 * yuan
 * 2020/2/14
 **/
@SuppressLint("AppCompatCustomView")
class DialogImageView extends ImageView {
    private final String TAG = this.getClass().getSimpleName();

    private Drawable imgDrawable;
    private Drawable closeDrawable;

    private int transY;

    private DialogSingleImage imageBuilder;

    public DialogImageView(Context context, DialogSingleImage dialogSingleImage) {
        super(context);
        imageBuilder = dialogSingleImage;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画布的中心为actionbar以外的屏幕中心
//        Log.d(TAG, "onDraw: " + getWidth() + " " + getHeight());    //除actionbar以外的宽高 720 1132
        //绘制图片
        imgDrawable = getDrawable();
        if (imgDrawable == null) imgDrawable = getResources().getDrawable(R.drawable.default_img);
//        Rect bound = imgDrawable.getBounds();
//        Log.d(TAG, "onDraw: " + imgDrawable.getIntrinsicWidth() + " " + imgDrawable.getIntrinsicHeight());//720, 917
//        Log.d(TAG, "onDraw: " + bound.top + " " + bound.bottom);    //0, 917
        int boundLeft = (getWidth() - (imgDrawable.getIntrinsicWidth()) / 2) / 2;
        int boundRight = boundLeft + (imgDrawable.getIntrinsicWidth() / 2);
        int boundTop = (getHeight() - (imgDrawable.getIntrinsicHeight() / 2)) / 2;
        int boundBottom = boundTop + (imgDrawable.getIntrinsicHeight() / 2);
        imgDrawable.setBounds(boundLeft, boundTop, boundRight, boundBottom);
        imgDrawable.draw(canvas);
        //绘制关闭图标
        Log.d(TAG, "onDraw: " + (boundBottom - boundTop));
        transY = ConvertUtil.dpToPx(getContext(), 40) + (boundBottom - boundTop) / 2;
        canvas.translate(0, transY);
        closeDrawable = getResources().getDrawable(R.drawable.icon_close);
        boundLeft = (getWidth() - (closeDrawable.getIntrinsicWidth()) / 2) / 2;
        boundRight = boundLeft + (closeDrawable.getIntrinsicWidth() / 2);
        boundTop = (getHeight() - (closeDrawable.getIntrinsicHeight() / 2)) / 2;
        boundBottom = boundTop + (closeDrawable.getIntrinsicHeight() / 2);
        closeDrawable.setBounds(boundLeft, boundTop, boundRight, boundBottom);
        closeDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        Log.d(TAG, "onTouchEvent:img width " + imgDrawable.getBounds().left + " " + imgDrawable.getBounds().right);
//        Log.d(TAG, "onTouchEvent:img height " + imgDrawable.getBounds().top + " " + imgDrawable.getBounds().bottom);
//        Log.d(TAG, "onTouchEvent:close width " + closeDrawable.getBounds().left + " " + closeDrawable.getBounds().right);
//        Log.d(TAG, "onTouchEvent:close height " + closeDrawable.getBounds().top + " " + closeDrawable.getBounds().bottom);
//        Log.d(TAG, "onTouchEvent: checkClose" +  event.getX() + " " + event.getY());
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (closeDrawable != null && closeDrawable.getBounds().contains((int) event.getX(), (int) event.getY() - transY)) {
                imageBuilder.dismiss();
            } else if (imgDrawable != null && imgDrawable.getBounds().contains((int) event.getX(), (int) event.getY())) {
                imageBuilder.onImageClick();
                imageBuilder.dismiss();
            }
        }
        return true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        imgDrawable = null;
        closeDrawable = null;
        imageBuilder = null;
    }
}
