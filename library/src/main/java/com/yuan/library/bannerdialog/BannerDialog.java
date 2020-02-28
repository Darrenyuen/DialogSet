package com.yuan.library.bannerdialog;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.yuan.library.R;

import java.util.List;

/**
 * yuan
 * 2020/2/22
 **/
public class BannerDialog{

    private ViewGroup decorView;
    private BannerDialogView bannerDialogView;

    private BannerDialog(@NonNull Activity context, Builder builder) {
        super();
        decorView = context.getWindow().getDecorView().findViewById(android.R.id.content);
        bannerDialogView = new BannerDialogView(context, builder);
        decorView.addView(bannerDialogView);
    }

    public static class Builder {

        private Activity context;
        private List<String> imageList;
        private int selectColor;
        private int normalColor;
        private OnImageClickListener onImageClickListener;

        public Builder(@NonNull final Activity context) {
            super();
            this.context = context;
            selectColor = context.getResources().getColor(R.color.selectColor);
            normalColor = context.getResources().getColor(R.color.normalColor);
            onImageClickListener = new OnImageClickListener() {
                @Override
                public void onImageClick(int index) {
                    Toast.makeText(context, "" + index, Toast.LENGTH_SHORT).show();
                }
            };
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

        List<String> getImageList() {
            return imageList;
        }

        int getSelectColor() {
            return selectColor;
        }

        int getNormalColor() {
            return normalColor;
        }

        LoadImageToView getLoadImageToView() {
            return new LoadImageToView() {
                @Override
                public void loadImageToView(ImageView imageView, String url) {
                    Glide.with(context).load(url).into(imageView);
                }
            };
        }

        public Builder onImageClickListener(OnImageClickListener onImageClickListener) {
            this.onImageClickListener = onImageClickListener;
            return this;
        }

        Context getContext() {
            return context;
        }

        OnImageClickListener getOnImageClickListener() {
            return onImageClickListener;
        }

        public void build() {
            new BannerDialog(context, this);
        }

    }
}
