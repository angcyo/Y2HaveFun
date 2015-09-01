package com.angcyo.y2havefun;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.RelativeLayout;

import com.angcyo.y2havefun.util.PopupTipWindow;
import com.angcyo.y2havefun.view.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    static int index = 0;
    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.contain)
    RelativeLayout mContain;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initTabs();
    }

    private void initTabs() {
        mViewPager.setAdapter(new ViewPagerAdapter(this.getSupportFragmentManager()));
        mTabs.setupWithViewPager(mViewPager);

        mTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                PopupTipWindow.showTip(MainActivity.this, (index++) % 4, "hello tip " + index);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initAfter() {

    }

    @Override
    protected void initBefore() {
        super.initBefore();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
