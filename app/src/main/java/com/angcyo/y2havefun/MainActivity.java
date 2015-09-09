package com.angcyo.y2havefun;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.angcyo.y2havefun.components.RDataService;
import com.angcyo.y2havefun.util.PrettyToast;
import com.angcyo.y2havefun.view.adapter.MainViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

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
    protected void init() {
        super.init();
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        EventBus.getDefault().register(this);
        initTabs();

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.action_settings) {
                    PrettyToast.show(MainActivity.this, "谢谢支持...^_^");
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }

    private void initTabs() {
        mViewPager.setAdapter(new MainViewPagerAdapter(this.getSupportFragmentManager()));
        mTabs.setupWithViewPager(mViewPager);

        mTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                PopupTipWindow.showTip(MainActivity.this, (index++) % 4, "hello tip " + index);
                mViewPager.setCurrentItem(tab.getPosition());
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
        mViewPager.setCurrentItem(RDataService.DATA_TYPE_1);
    }

    @Override
    protected void initBefore() {
        super.initBefore();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    private MainViewPagerAdapter getViewPagerAdapter() {
        if (mViewPager != null) {
            return (MainViewPagerAdapter) mViewPager.getAdapter();
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(mNavView)) {
            mDrawerLayout.closeDrawer(mNavView);
        } else {
            super.onBackPressed();
        }
    }
}

