package com.angcyo.y2havefun;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.tabs)
    TabLayout mTabs;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
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
    }

    @Override
    protected void initAfter() {

    }

    @Override
    protected void initBefore() {
        super.initBefore();

    }
}
