package com.yuan.library.bottomdialog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuan.dialogset.utils.ConvertUtil;
import com.yuan.dialogset.utils.LayoutUtil;

import java.util.List;

/**
 * yuan
 * 2020/2/12
 **/
public class BottomDialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = this.getClass().getSimpleName();

    private OnItemClickListener onItemClickListener;
    private int orientation;
    private int count;
    private List<Item> list;
    private int padding;
    private int itemSize;
    private int paddingInItem;
    private int itemTextColor;

    public BottomDialogAdapter(OnItemClickListener onItemClickListener, int orientation, int count, List<Item> list, int padding, int paddingInItem, int itemSize, int itemTextColor) {
        super();
        this.onItemClickListener = onItemClickListener;
        this.orientation = orientation;
        this.count = count;
        this.list = list;
        this.padding = padding;
        this.paddingInItem = paddingInItem;
        this.itemSize = itemSize;
        this.itemTextColor = itemTextColor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return orientation == 0 ? new VerticalViewHolder(new LinearLayout(parent.getContext())) : new HorizontalViewHolder(new GridLayout(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Item item = list.get(position);

        if (orientation == 0) {
            VerticalViewHolder verticalViewHolder = (VerticalViewHolder) holder;
            verticalViewHolder.item.setText(item.getTitle());
            verticalViewHolder.item.setCompoundDrawablesWithIntrinsicBounds(verticalViewHolder.getIcon(item.getIcon()), null, null, null );
            verticalViewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.click(item);
                }
            });
        } else {
            HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) holder;
            horizontalViewHolder.item.setText(item.getTitle());
            horizontalViewHolder.item.setCompoundDrawablesWithIntrinsicBounds(null, horizontalViewHolder.getIcon(item.getIcon()), null, null);
            horizontalViewHolder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.click(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VerticalViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        private TextView item;

        public VerticalViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            item = new TextView(itemView.getContext());
            item.setTextSize(itemSize);
            item.setMaxLines(1);
            item.setTextColor(itemTextColor);
            item.setGravity(Gravity.CENTER_VERTICAL);
            item.setCompoundDrawablePadding(ConvertUtil.dpToPx(context, paddingInItem));
            item.setPadding(ConvertUtil.dpToPx(context, padding), 0, 0, ConvertUtil.dpToPx(context, padding));
            ((LinearLayout) itemView).addView(item);
        }

        private Drawable getIcon(Drawable drawable) {
            if (drawable != null) {
//                int width = drawable.getIntrinsicWidth();
//                int height = drawable.getIntrinsicHeight();
//                Log.d(TAG, "icon: " + width + " " + height);
                Bitmap bitmap = ConvertUtil.drawableToBitmap(drawable);
//                Log.d(TAG, "icon: " + oldBmp.getWidth() + " " + oldBmp.getHeight());
                return new BitmapDrawable(bitmap);
            }
            return null;
        }
    }

    class HorizontalViewHolder extends RecyclerView.ViewHolder {
        private Context context;
        private TextView item;

        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.width = LayoutUtil.getScreenWidth(context) / count;
            item = new TextView(context);
            item.setLayoutParams(params);
            item.setTextSize(itemSize);
            item.setMaxLines(1);
            item.setTextColor(itemTextColor);
            item.setGravity(Gravity.CENTER_HORIZONTAL);
            item.setCompoundDrawablePadding(ConvertUtil.dpToPx(context, paddingInItem));
            item.setPadding(ConvertUtil.dpToPx(context, padding), 0, 0, ConvertUtil.dpToPx(context, padding));
            ((GridLayout) itemView).addView(item);
        }

        private Drawable getIcon(Drawable drawable) {
            if (drawable != null) {
                Bitmap bitmap = ConvertUtil.drawableToBitmap(drawable);
                return new BitmapDrawable(bitmap);
            }
            return null;
        }
    }
}
