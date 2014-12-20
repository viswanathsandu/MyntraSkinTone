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
public class PhotosRecyclerAdapter extends RecyclerView.Adapter<PhotosRecyclerAdapter.ViewHolder> {

    private List<String> itemsData;
    private Context mContext;
    private ImageLoader mImageLoader;
    OnItemClickListener mItemClickListener;

    public PhotosRecyclerAdapter(Context context, List<String> itemsData) {
        this.mContext = context;
        this.itemsData = itemsData;
        mImageLoader = ImageLoader.getInstance();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    @Override
    public PhotosRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photos_recycler_item_view, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        mImageLoader.displayImage(UrlUtils.getUrlFor(position + 1), viewHolder.imageView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imageView = (ImageView) itemLayoutView.findViewById(R.id.imageview);
            itemLayoutView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }
}