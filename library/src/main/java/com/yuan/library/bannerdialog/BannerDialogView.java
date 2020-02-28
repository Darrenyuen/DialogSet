package com.yuan.library.bannerdialog;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.yuan.library.R;

import java.util.LinkedList;
import java.util.List;

/**
 * yuan
 * 2020/2/22
 **/
public class BannerDialogView extends FrameLayout implements View.OnClickListener{

    private BannerDialog.Builder builder;

    private Activity context;
    private BannerPagerAdapter bannerPagerAdapter;
    private ViewPager viewPager;
    private Paint paint;
    private Drawable closeDrawable;

    float firstX = 0;
    float firstY = 0;

    public BannerDialogView(@NonNull Activity context, @NonNull BannerDialog.Builder builder) {
        super(context);
        this.context = context;
        this.builder = builder;
        init();

    }

    private void init() {

        setWillNotDraw(false);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);

        List<ImageView> imageViewList = new LinkedList<>();
        for (int i = 0; i < builder.getImageList().size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setOnClickListener(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewList.add(imageView);
        }

        bannerPagerAdapter = new BannerPagerAdapter(builder, imageViewList);
        viewPager = new ViewPager(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(300, 500, Gravity.CENTER);
        this.addView(viewPager, layoutParams);
        viewPager.setAdapter(bannerPagerAdapter);
    }

    /**
     * 绘制指示圆点与关闭图标
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = getWidth();
        float height = getHeight();
        int count = builder.getImageList().size();
        float circleRSize = 5.0f;
        float margin = circleRSize * 1.5f;
        float cY = viewPager.getHeight() + 340;
        double cX = width / 2.0 - (2 * circleRSize * count / 2.0 + margin * (count - 1) / 2.0) + circleRSize;

        for (int i = 0; i < count; i++) {
            if (viewPager.getCurrentItem() == i) paint.setColor(builder.getSelectColor());
            else paint.setColor(builder.getNormalColor());
            if (i != 0) {
                cX += circleRSize * 2 + margin;
            }
            canvas.drawCircle((float) cX, cY, circleRSize, paint);
        }
        closeDrawable = getResources().getDrawable(R.drawable.icon_close);
        closeDrawable.setBounds((int) width / 2 - 20, (int) height / 2 + viewPager.getHeight() / 2 + 50, (int) width / 2 + 20, (int) height / 2 + viewPager.getHeight() / 2 + 90);
        closeDrawable.draw(canvas);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                firstX = event.getX();
                firstY = event.getY();
        }
        if (viewPager.getLeft() < firstX && viewPager.getRight() > firstX && viewPager.getTop() < firstY && viewPager.getBottom() > firstY) return super.dispatchTouchEvent(event);
        else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (closeDrawable!= null && closeDrawable.getBounds().contains((int) event.getX(), (int) event.getY())) {
                ViewGroup decorView = context.getWindow().getDecorView().findViewById(android.R.id.content);
                decorView.removeView(BannerDialogView.this);
            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        builder.getOnImageClickListener().onImageClick(viewPager.getCurrentItem());
    }

    @Override
    protected void onDetachedFromWindow() {
        builder = null;
        bannerPagerAdapter = null;
        viewPager = null;
        paint = null;
        closeDrawable = null;
        super.onDetachedFromWindow();
    }
}
