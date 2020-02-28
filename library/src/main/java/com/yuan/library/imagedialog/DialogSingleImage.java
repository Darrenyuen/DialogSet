package com.yuan.library.imagedialog;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;

/**
 * yuan
 * 2020/2/15
 **/
public class DialogSingleImage {

    private ViewGroup decorView;
    private DialogImageView imageView;
    private OnImageClickListener onImageClickListener;
    private Context context;
    private String imageURL;

    private DialogSingleImage(Activity context, String imageURL, OnImageClickListener imageClickListener) {
        super();
        this.context = context;
        imageView = new DialogImageView(context, this);
        onImageClickListener = imageClickListener;
        this.imageURL = imageURL;
        decorView = context.getWindow().getDecorView().findViewById(android.R.id.content);
        decorView.addView(imageView);
        show();
    }

    private void show() {
        Glide.with(context)
                .load(imageURL)
                .into(imageView);
    }

    void dismiss() {
        decorView.removeView(imageView);
    }

    void onImageClick() {
        onImageClickListener.onImageClickListener();
    }

    public static class Builder{
        private String imageURL;
        private Context context;
        private OnImageClickListener onImageClickListener;

        public Builder imageURL(String imageURL) {
            this.imageURL = imageURL;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder onImageClickListener(OnImageClickListener onImageClickListener) {
            this.onImageClickListener = onImageClickListener;
            return this;
        }

        public void build() {
            new DialogSingleImage((Activity) context, imageURL, onImageClickListener).show();
        }
    }

}
