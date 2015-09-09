package com.angcyo.y2havefun.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.angcyo.y2havefun.components.RDataService;
import com.angcyo.y2havefun.util.Logger;
import com.angcyo.y2havefun.view.CommonFragment;

/**
 * Created by angcyo on 15-08-31-031.
 */
public class ViewPagerStateAdapter extends FragmentStatePagerAdapter {
    private String[] tabTitles = new String[]{"6贱事", "叽歪笑话", "捧腹网", "巨有趣", "来福岛", "我恶搞"};

    public ViewPagerStateAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Logger.e("position  " + position);
        Fragment fragment = new CommonFragment();
        Bundle args = new Bundle();
        args.putInt(RDataService.KEY_DATA_TYPE, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
