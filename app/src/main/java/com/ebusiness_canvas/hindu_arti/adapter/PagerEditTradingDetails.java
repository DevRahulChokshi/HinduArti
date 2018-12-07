package com.ebusiness_canvas.hindu_arti.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ebusiness_canvas.hindu_arti.fragments.KundaliFragment;
import com.ebusiness_canvas.hindu_arti.fragments.KundaliMatchingFragment;

/**
 * Created by developer on 11-03-2018.
 */

public class PagerEditTradingDetails extends FragmentStatePagerAdapter {

    int tabCount;

    public PagerEditTradingDetails(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount= tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new KundaliFragment();
            case 1:

                return new KundaliMatchingFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}