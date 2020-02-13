package com.yuan.library.bottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuan.dialogset.R;

import java.util.ArrayList;
import java.util.List;

/**
 * yuan
 * 2020/2/12
 **/
public class BottomDialog extends Dialog{

    private String TAG = this.getClass().getSimpleName();

    private BottomDialogAdapter adapter;
    private List<Item> items;

    private String title;
    private TextView titleTextView;
    private int menu;
    private int margin;
    private int iconSize;
    private int itemSize;
    private OnItemClickListener onItemClickListener;

    LinearLayout container;


    public BottomDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.BottomDialog);
        this.title = builder.title;
        this.menu = builder.menu;
        this.margin = builder.margin;
        this.iconSize = builder.iconSize;
        this.itemSize = builder.itemSize;
        this.onItemClickListener = builder.onItemClickListener;
        setContentView(R.layout.bottom_dialog);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        container = findViewById(R.id.container);
        if (title != null) {
            titleTextView = findViewById(R.id.title);
            titleTextView.setText(title);
            titleTextView.setTextSize(builder.titleSize);
            titleTextView.setVisibility(View.VISIBLE);
        }
        setCanceledOnTouchOutside(builder.isCanceledTouchOutside);
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        inflateMenu(menu);
        itemsAttachView();
    }

    /**
     * 拿到用户传入的menu数据
     * @param menu
     */
    private void inflateMenu(int menu) {
        MenuInflater inflater = new SupportMenuInflater(getContext());
        MenuBuilder menuBuilder = new MenuBuilder(getContext());
        inflater.inflate(menu, menuBuilder);
        items = new ArrayList<>();
        for (int i = 0; i < menuBuilder.size(); i++) {
            MenuItem menuItem = menuBuilder.getItem(i);
            items.add(new Item(menuItem.getItemId(), menuItem.getTitle().toString(), menuItem.getIcon()));
        }
        Log.d(TAG, "inflateMenu: " + items.size());
    }

    /**
     * 展示用户自定义的menu数据
     */
    private void itemsAttachView() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        adapter = new BottomDialogAdapter(onItemClickListener, items, margin, iconSize, itemSize);

        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(params);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        container.addView(recyclerView);
    }

    /**
     * 负责接受用户自定义的参数
     */
    public static class Builder {


        private Context context;
        private String title;
        private int titleSize = 20;
        private int menu;
        private int margin = 0;
        private int iconSize;
        private int itemSize = 16;
        private OnItemClickListener onItemClickListener;
        private boolean isCanceledTouchOutside = true;


        public Builder(@NonNull Context context) {
            this.context = context;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder titleSize(int titleSize) {
            this.titleSize = titleSize;
            return this;
        }

        public Builder menu(int menu) {
            this.menu = menu;
            return this;
        }

        public Builder marginLeft(int margin) {
            this.margin = margin;
            return this;
        }

        public Builder iconSize(int iconSize) {
            this.iconSize = iconSize;
            return this;
        }

        public Builder itemSize(int itemSize) {
            this.itemSize = itemSize;
            return this;
        }

        public Builder onItemClickListener(OnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
            return this;
        }

        public Builder isCanceled(boolean isCanceledTouchOutside) {
            this.isCanceledTouchOutside = isCanceledTouchOutside;
            return this;
        }

        public void build() {
            new BottomDialog(context, this).show();
        }

    }
}
