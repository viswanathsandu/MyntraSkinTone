package com.myntra.myntraskintone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.myntra.myntraskintone.fragments.PhotoFragment;

import java.util.List;

/**
 * Created by viswanath.sandu on 20/12/14.
 */
public class RaceAdapter extends FragmentPagerAdapter {
    private int mNoOfItems;

    public RaceAdapter(FragmentManager fragmentManager, int noOfItems) {
        super(fragmentManager);
        this.mNoOfItems = noOfItems;
    }

    @Override
    public int getCount() {
        return mNoOfItems;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoFragment.newInstance(position+1);
    }

}