package com.myntra.myntraskintone.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.myntra.myntraskintone.R;
import com.myntra.myntraskintone.adapter.TonesRecyclerAdapter;
import com.myntra.myntraskintone.util.UrlUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class PhotoFragment extends android.support.v4.app.Fragment implements ImageLoadingListener, TonesRecyclerAdapter.OnItemClickListener {

    private OnFragmentInteractionListener mListener;
    private static final String ARGS_POSITION = "position";
    private int position;
    private ImageLoader imageLoader;
    private ImageView originalImageView, skinlessImageview, filterImage;
    private ProgressBar mProgressbar;
    private SeekBar lightingSeekbar;
    private RecyclerView tonesRecyclerView;
    private static float opacity = 0;

    public static PhotoFragment newInstance(int position) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    public PhotoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARGS_POSITION, 0);
        imageLoader = ImageLoader.getInstance();
        if(!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo, container, false);
        init(rootView);
        loadOriginalImage();
        loadTonesIntoRecyclerView();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(opacity > 0) {
            mProgressbar.setProgress((int)(opacity/0.01f));
        }
    }

    private void init(View rootView) {
        tonesRecyclerView = (RecyclerView) rootView.findViewById(R.id.tones_recycler_view);
        originalImageView = (ImageView) rootView.findViewById(R.id.original_image);
        skinlessImageview = (ImageView) rootView.findViewById(R.id.skinless_image);
        filterImage = (ImageView) rootView.findViewById(R.id.filter_image);
        mProgressbar = (ProgressBar) rootView.findViewById(R.id.progressbar);
        lightingSeekbar = (SeekBar) rootView.findViewById(R.id.lighting_seekbar);
        lightingSeekbar.setOnSeekBarChangeListener(lightingSeekbarChangeListener);
    }

    private void loadTonesIntoRecyclerView() {
        TonesRecyclerAdapter adapter = new TonesRecyclerAdapter(getActivity(), getResources().getIntArray(R.array.tone_colors));
        adapter.setOnItemClickListener(this);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        tonesRecyclerView.setLayoutManager(mLinearLayoutManager);
        tonesRecyclerView.setAdapter(adapter);
    }

    private SeekBar.OnSeekBarChangeListener lightingSeekbarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            opacity = progress * 0.01f;
            filterImage.setAlpha(opacity);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void loadOriginalImage() {
        imageLoader.displayImage(UrlUtils.getUrlFor(position), originalImageView, this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onLoadingStarted(String s, View view) {
        mProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoadingComplete(String s, View view, Bitmap bitmap) {
        if(view.getId() == originalImageView.getId()) {
            imageLoader.displayImage(UrlUtils.getSkinlessUrlFor(position), skinlessImageview, this);
        } else {
            mProgressbar.setVisibility(View.GONE);
        }
    }


    @Override
    public void onLoadingCancelled(String s, View view) {
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onLoadingFailed(String s, View view, FailReason failReason) {
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(View view, int item) {
        filterImage.setBackgroundColor(item);
        filterImage.setAlpha(opacity);
    }
}
