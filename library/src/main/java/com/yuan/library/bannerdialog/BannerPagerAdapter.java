package com.yuan.library.bannerdialog;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * yuan
 * 2020/2/22
 **/
class BannerPagerAdapter extends PagerAdapter {

    private List<String> urlList;
    private List<ImageView> imageViewList;
    private LoadImageToView loadImageToView;

    BannerPagerAdapter(BannerDialog.Builder builder, List<ImageView> imageViewList) {
        super();
        this.urlList = builder.getImageList();
        this.imageViewList = imageViewList;
        this.loadImageToView = builder.getLoadImageToView();
    }

    @Override
    public int getCount() {
        return urlList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = imageViewList.get(position);
        if (imageView.getDrawable() == null) {
            if (loadImageToView != null) {
                loadImageToView.loadImageToView(imageView, urlList.get(position));
                imageView.setPadding(3, 3, 0, 0);
            }
        }
        container.addView(imageView, new ViewGroup.LayoutParams(500, 500));
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
