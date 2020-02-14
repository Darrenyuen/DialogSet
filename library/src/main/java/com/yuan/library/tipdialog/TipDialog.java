package com.yuan.library.tipdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.yuan.library.R;
import com.yuan.library.utils.LayoutUtil;


/**
 * yuan
 * 2020/2/13
 **/
public class TipDialog extends Dialog {

    private TextView titleTextView;
    private TextView contentTextView;
    private TextView positiveButton;
    private TextView negativeButton;

    private View.OnClickListener positiveOnClickListener;
    private View.OnClickListener negativeOnClickListener;
    private String title = "提示";
    private int titleColor;
    private int titleSize = 18;
    private String content = "";
    private int contentColor;
    private int contentSize = 18;
    private int negativeColor;
    private int positiveColor;

    public TipDialog(@NonNull Context context) {
        super(context, R.style.TipDialog);
        setContentView(R.layout.tip_dialog);
        titleColor = context.getResources().getColor(R.color.textColorBlack);
        contentColor = context.getResources().getColor(R.color.textColorGrey);
        negativeColor = context.getResources().getColor(R.color.textColorRed);
        positiveColor = context.getResources().getColor(R.color.textColorBlack);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(LayoutUtil.getScreenWidth(context) / 3 * 2, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public void positiveOnClickListener(View.OnClickListener positiveOnClickListener) {
        this.positiveOnClickListener = positiveOnClickListener;
    }

    public void negativeOnClickListener(View.OnClickListener negativeOnClickListener) {
        this.negativeOnClickListener = negativeOnClickListener;
    }

    public void title(String title) {
        this.title = title;
    }

    public void titleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public void titleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public void content(String content) {
        this.content = content;
    }

    public void contentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    public void contentColor(int contentColor) {
        this.contentColor = contentColor;
    }

    public void negativeColor(int negativeColor) {
        this.negativeColor = negativeColor;
    }

    public void positiveColor(int positiveColor) {
        this.positiveColor = positiveColor;
    }

    @Override
    public void show() {
        titleTextView = findViewById(R.id.title);
        contentTextView = findViewById(R.id.content);
        negativeButton = findViewById(R.id.negative);
        positiveButton = findViewById(R.id.positive);

        titleTextView.setText(title);
        titleTextView.setTextSize(titleSize);
        titleTextView.setTextColor(titleColor);

        contentTextView.setText(content);
        contentTextView.setTextSize(contentSize);
        contentTextView.setTextColor(contentColor);

        negativeButton.setOnClickListener(negativeOnClickListener);
        negativeButton.setTextColor(negativeColor);

        positiveButton.setOnClickListener(positiveOnClickListener);
        positiveButton.setTextColor(positiveColor);

        super.show();
    }

}
