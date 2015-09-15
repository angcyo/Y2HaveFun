package com.angcyo.y2havefun;

import android.support.v4.view.ViewPager;

import com.angcyo.y2havefun.components.RDataService;
import com.angcyo.y2havefun.control.DataControl;
import com.angcyo.y2havefun.view.adapter.DetailsViewPagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by angcyo on 15-09-09-009.
 */
public class DetailsActivity extends BaseActivity {
    public static String KEY_POSITION = "key_type";
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    int type = RDataService.DATA_TYPE_0;//网站类型
    int position = 0;//第几张图片

    @Override
    protected void init() {
        super.init();
        position = getIntent().getIntExtra(KEY_POSITION, position);
        type = getIntent().getIntExtra(RDataService.KEY_DATA_TYPE, type);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activiti_details);
        ButterKnife.bind(this);
        initWindow(android.support.design.R.color.accent_material_light);
    }

    @Override
    protected void initAfter() {
        viewpager.setAdapter(new DetailsViewPagerAdapter(DataControl.getData(type)));
        viewpager.setCurrentItem(position);
    }
}
