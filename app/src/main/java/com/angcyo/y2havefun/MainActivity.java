package com.angcyo.y2havefun;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.RelativeLayout;

import com.angcyo.y2havefun.view.adapter.ViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tabs)
    TabLayout mTabs;
//    @Bind(R.id.recyclerView)
//    RecyclerView mRecyclerView;

    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.refresher)
    SwipeRefreshLayout mRefresher;
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
//        mRefresher.setEnabled(false);
        mRefresher.setRefreshing(true);

        initTabs();
    }

    private void initTabs() {
//        mTabs.addTab();
        mViewPager.setAdapter(new ViewPagerAdapter(this.getSupportFragmentManager()));
        mTabs.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initAfter() {

    }

    @Override
    protected void initBefore() {
        super.initBefore();

    }
}
