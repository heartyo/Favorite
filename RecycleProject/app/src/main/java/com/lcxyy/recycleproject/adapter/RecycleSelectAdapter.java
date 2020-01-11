package com.lcxyy.recycleproject.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lcxyy.recycleproject.R;
import com.lcxyy.recycleproject.entity.SelectEntity;

import java.util.List;

public class RecycleSelectAdapter extends RecyclerView.Adapter<RecycleSelectAdapter.ViewHolder> {


    private List<SelectEntity> souces;
    private int selectedPosition = -1;

    private Context context;

    public void setSouces(List<SelectEntity> souces) {
        this.souces = souces;
    }

    public RecycleSelectAdapter(List<SelectEntity> souces) {
        this.souces = souces;
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if (souces == null) {
            return;
        }
        SelectEntity selectEntity = souces.get(position);
        holder.textView.setText(selectEntity.getInfo());
        if (position == selectedPosition) {
            selectEntity.setSelected(!selectEntity.isSelected());
        } else {
            selectEntity.setSelected(false);
        }
        Drawable unSelectDrawable = context.getResources().getDrawable(R.drawable.shape_grey_border_white_bg);
        Drawable selectDrawable = context.getResources().getDrawable(R.drawable.shape_red_border_white_bg);
        holder.itemView.setBackground(selectEntity.isSelected() ? selectDrawable : unSelectDrawable);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClck(holder.itemView, position);
                    selectedPosition = position; //选择的position赋值给参数，
                    notifyDataSetChanged();
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return souces == null ? 0 : souces.size();
    }

    public interface OnItemClickListener {
        void onItemClck(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_info);

        }


    }
}
