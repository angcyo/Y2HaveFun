package com.angcyo.y2havefun.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.angcyo.y2havefun.view.CommonFragment;

/**
 * Created by angcyo on 15-08-31-031.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] tabTitles = new String[]{"6贱事", "叽歪笑话", "捧腹网", "巨有趣", "来福岛", "我恶搞"};

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new CommonFragment();
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
