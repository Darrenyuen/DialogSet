package com.yuan.library.bannerdialog;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
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

    private BannerPagerAdapter bannerPagerAdapter;
    private ViewPager viewPager;
    private Paint paint;

    public BannerDialogView(@NonNull Activity context, @NonNull BannerDialog.Builder builder) {
        super(context);
        this.builder = builder;
        init();
    }

    private void init() {

        setWillNotDraw(false);

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(4);

        List<ImageView> imageViewList = new LinkedList<>();
        for (int i = 0; i < builder.getImageList().size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setOnClickListener(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageViewList.add(imageView);
        }

        bannerPagerAdapter = new BannerPagerAdapter(builder.getImageList(), imageViewList, builder.getLoadImageToView());
        viewPager = new ViewPager(getContext());
        this.addView(viewPager, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewPager.setAdapter(bannerPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (builder.getImageList().size() >= 2) {
                    if (position == 0 && positionOffset == 0)
                        viewPager.setCurrentItem(builder.getImageList().size() - 2, false);
                    else if (position == builder.getImageList().size() - 1 && positionOffset == 0)
                        viewPager.setCurrentItem(1, false);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 绘制指示圆点
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

    }

}
