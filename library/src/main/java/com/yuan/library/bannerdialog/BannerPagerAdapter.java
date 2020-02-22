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

    BannerPagerAdapter(List<String> urlList, List<ImageView> imageViewList, LoadImageToView loadImageToView) {
        super();
        this.urlList = urlList;
        this.imageViewList = imageViewList;
        this.loadImageToView = loadImageToView;
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
            }
        }
        container.addView(imageView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
