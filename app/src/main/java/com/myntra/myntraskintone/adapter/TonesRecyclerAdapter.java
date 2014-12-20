package com.myntra.myntraskintone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.myntra.myntraskintone.R;
import com.myntra.myntraskintone.util.UrlUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


/**
 * Created by viswanath.sandu on 18/12/14.
 */
public class TonesRecyclerAdapter extends RecyclerView.Adapter<TonesRecyclerAdapter.ViewHolder> {

    private int [] itemsData; // It'll hold the tone color ids
    private Context mContext;
    OnItemClickListener mItemClickListener;

    public TonesRecyclerAdapter(Context context, int [] itemsData) {
        this.mContext = context;
        this.itemsData = itemsData;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public TonesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tones_recycler_item_view, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.toneColorImageView.setBackgroundColor(itemsData[position]);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView toneColorImageView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            toneColorImageView = (ImageView) itemLayoutView.findViewById(R.id.tone_color_imageview);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null) {
                mItemClickListener.onItemClick(v, itemsData[getPosition()]);
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int item);
    }
}