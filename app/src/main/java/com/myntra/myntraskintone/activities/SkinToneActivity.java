package com.myntra.myntraskintone.activities;

import com.myntra.myntraskintone.R;
import com.myntra.myntraskintone.adapter.PhotosRecyclerAdapter;
import com.myntra.myntraskintone.util.UrlUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

public class SkinToneActivity extends Activity implements PhotosRecyclerAdapter.OnItemClickListener {

    private android.support.v7.widget.RecyclerView mPhotosRecyclerView;
    private SeekBar mOpacitySeekbar;
    private ImageView skinnedImage;
    private ImageView skinLessImage;
    ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_tone);
        imageLoader = ImageLoader.getInstance();
        if(!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        }
        initUI();
    }

    private void initUI() {
        skinnedImage = (ImageView) findViewById(R.id.original_image);
        skinLessImage = (ImageView) findViewById(R.id.transparent_skin_image);
        mOpacitySeekbar = (SeekBar) findViewById(R.id.seekBar);
        mOpacitySeekbar.setMax(50);
        mOpacitySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                skinLessImage.setAlpha(progress * 0.01f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        mPhotosRecyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.photo_recycler_view);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mPhotosRecyclerView.setLayoutManager(mLinearLayoutManager);
        PhotosRecyclerAdapter adapter = new PhotosRecyclerAdapter(this, getData());
        adapter.setOnItemClickListener(this);
        mPhotosRecyclerView.setAdapter(adapter);

    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        for(int i=1; i<=20; i++) {
            data.add(i+"");
        }
        return data;
    }

    @Override
    public void onItemClick(View view, final int position) {
        imageLoader.displayImage(UrlUtils.getUrlFor(position + 1), skinnedImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) { }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {}

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                imageLoader.displayImage(UrlUtils.getSkinlessUrlFor(position + 1), skinLessImage, skinlessImageLoadingListener);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {}
        });
    }

    private ImageLoadingListener skinlessImageLoadingListener = new ImageLoadingListener() {
        @Override
        public void onLoadingStarted(String s, View view) {

        }

        @Override
        public void onLoadingFailed(String s, View view, FailReason failReason) {

        }

        @Override
        public void onLoadingComplete(String s, View view, Bitmap bitmap) {

        }

        @Override
        public void onLoadingCancelled(String s, View view) {

        }
    };
}
