package com.yuan.library.bottomdialog;

import android.app.Dialog;
import android.content.Context;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yuan.library.R;

import java.util.ArrayList;
import java.util.List;

/**
 * yuan
 * 2020/2/12
 **/
public class BottomDialog extends Dialog{

    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;

    private BottomDialogAdapter adapter;
    private List<Item> items;

    private int orientation;
    private int count;
    private String title;
    private TextView titleTextView;
    private int menu;
    private int padding;
    private int paddingInItem;
    private int itemSize;
    private int itemTextColor;
    private OnItemClickListener onItemClickListener;

    LinearLayout container;

    private BottomDialog(@NonNull Context context, Builder builder) {
        super(context, R.style.BottomDialog);
        this.orientation = builder.orientation;
        this.count = builder.count;
        this.title = builder.title;
        this.menu = builder.menu;
        this.padding = builder.padding;
        this.paddingInItem = builder.paddingInItem;
        this.itemSize = builder.itemSize;
        this.itemTextColor = builder.itemTextColor;
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

    private void inflateMenu(int menu) {
        MenuInflater inflater = new SupportMenuInflater(getContext());
        MenuBuilder menuBuilder = new MenuBuilder(getContext());
        inflater.inflate(menu, menuBuilder);
        items = new ArrayList<>();
        for (int i = 0; i < menuBuilder.size(); i++) {
            MenuItem menuItem = menuBuilder.getItem(i);
            items.add(new Item(menuItem.getItemId(), menuItem.getTitle().toString(), menuItem.getIcon()));
        }
    }

    private void itemsAttachView() {
        RecyclerView.LayoutManager manager;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (orientation == 0) {
             manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        } else {
            manager = new GridLayoutManager(getContext(), count);
        }

        adapter = new BottomDialogAdapter(onItemClickListener, orientation, count, items, padding, paddingInItem, itemSize, itemTextColor);

        RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(params);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        container.addView(recyclerView);
    }

    public static class Builder {

        private Context context;
        private int orientation = 0;
        private int count = 5;
        private String title;
        private int titleSize = 20;
        private int menu;
        private int padding = 5;
        private int paddingInItem = 10;
        private int itemSize = 16;
        private int itemTextColor;
        private OnItemClickListener onItemClickListener;
        private boolean isCanceledTouchOutside = true;


        public Builder(@NonNull Context context) {
            this.context = context;
            itemTextColor = context.getResources().getColor(R.color.textColorGrey);
        }

        public Builder orientation(int orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder count(int count) {
            this.count = count;
            return this;
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

        public Builder padding(int padding) {
            this.padding = padding;
            return this;
        }

        public Builder paddingInItem(int paddingInItem) {
            this.paddingInItem = paddingInItem;
            return this;
        }

        public Builder itemSize(int itemSize) {
            this.itemSize = itemSize;
            return this;
        }

        public Builder itemTextColor(int itemTextColor) {
            this.itemTextColor = itemTextColor;
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
