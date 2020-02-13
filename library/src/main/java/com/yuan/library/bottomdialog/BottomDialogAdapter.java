package com.yuan.library.bottomdialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * yuan
 * 2020/2/12
 **/
public class BottomDialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener onItemClickListener;
    private List<Item> list;
    private int margin;
    private int itemSize;
    private int iconSize;

    public BottomDialogAdapter(OnItemClickListener onItemClickListener, List<Item> list, int margin, int iconSize, int itemSize) {
        super();
        this.onItemClickListener = onItemClickListener;
        this.list = list;
        this.margin = margin;
        this.iconSize = iconSize;
        this.itemSize = itemSize;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VerticalViewHolder(new LinearLayout(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Item item = list.get(position);

        VerticalViewHolder verticalViewHolder = (VerticalViewHolder) holder;
        verticalViewHolder.item.setText(item.getTitle());
        verticalViewHolder.item.setCompoundDrawablesWithIntrinsicBounds(verticalViewHolder.icon(item.getIcon()), null, null, null );
        verticalViewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.click(item);
            }
        });
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
            ((LinearLayout) itemView).addView(item);
        }

        private Drawable icon(Drawable drawable) {
            if (drawable != null) {
//                Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//                @SuppressWarnings("SuspiciousNameCombination") Drawable resizeIcon = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap((), 32, 32, true));
//                Drawable.ConstantState state = resizeIcon.getConstantState();
//                resizeIcon = DrawableCompat.wrap(state == null ? resizeIcon : state.newDrawable().mutate());
//                return resizeIcon;
                return drawable;
            }
            return null;
        }
    }
}
