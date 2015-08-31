package com.angcyo.y2havefun.view;

import com.angcyo.y2havefun.control.HttpAsync;
import com.angcyo.y2havefun.mode.ContentItem;

import java.util.List;

/**
 * Created by angcyo on 15-08-31-031.
 */
public class CommonFragment extends BaseFragment {
    HttpAsync httpAsync;

    @Override
    protected void loadData() {
        httpAsync = new HttpAsync() {
            @Override
            public void onPostExe(List<ContentItem> contentItems) {

            }
        };
    }

    @Override
    protected void initView() {
        recyclerView.setAdapter(null);
    }
}
