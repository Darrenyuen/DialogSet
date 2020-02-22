package com.yuan.library.bannerdialog;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.yuan.library.R;

import java.util.List;

/**
 * yuan
 * 2020/2/22
 **/
public class BannerDialog {

    private ViewGroup decorView;
    private BannerDialogView bannerDialogView;

    private BannerDialog(@NonNull Activity context, Builder builder) {
        super();
        decorView = context.getWindow().getDecorView().findViewById(android.R.id.content);
        bannerDialogView = new BannerDialogView(context, builder);
        decorView.addView(bannerDialogView);
    }

    private void dismiss() {
        decorView.removeView(bannerDialogView);
    }

    public static class Builder {

        private Activity context;
        private List<String> imageList;
        private int selectColor;
        private int normalColor;

        public Builder(@NonNull Activity context) {
            super();
            this.context = context;
            selectColor = context.getResources().getColor(R.color.textColorGrey);
            normalColor = context.getResources().getColor(R.color.bg_white);
        }

        public Builder imageList(List<String> imageList) {
            this.imageList = imageList;
            return this;
        }

        public Builder selectColor(int selectColor) {
            this.selectColor = selectColor;
            return this;
        }

        public Builder normalColor(int normalColor) {
            this.normalColor = normalColor;
            return this;
        }

        public List<String> getImageList() {
            return imageList;
        }

        public int getSelectColor() {
            return selectColor;
        }

        public int getNormalColor() {
            return normalColor;
        }

        public LoadImageToView getLoadImageToView() {
            return new LoadImageToView() {
                @Override
                public void loadImageToView(ImageView imageView, String url) {
                    Glide.with(context).load(url).into(imageView);
                }
            };
        }

        public void build() {
            new BannerDialog(context, this);
        }
    }
}
