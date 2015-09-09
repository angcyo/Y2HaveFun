package com.angcyo.y2havefun.view.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angcyo.y2havefun.R;
import com.angcyo.y2havefun.mode.ContentItem;
import com.angcyo.y2havefun.util.Util;
import com.angcyo.y2havefun.view.TouchImageView;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by angcyo on 15-09-09-009.
 */
public class DetailsViewPagerAdapter extends PagerAdapter {
    List<ContentItem> datas;

    @Bind(R.id.content)
    TextView content;
    @Bind(R.id.img_empty)
    TextView imgEmpty;
    @Bind(R.id.image)
    TouchImageView image;

    public DetailsViewPagerAdapter(List<ContentItem> datas) {
        this.datas = datas;
//        //过滤掉空图片
//        this.datas = new ArrayList<>();
//        for (ContentItem item : datas) {
//            if (!Util.isEmpty(item.getImgUrl())) {
//                this.datas.add(item);
//            }
//        }
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View rootView = View.inflate(container.getContext(), R.layout.adapter_detail_item, null);
        ButterKnife.bind(this, rootView);
        ContentItem item = datas.get(position);
        if (Util.isEmpty(item.getImgUrl())) {
            imgEmpty.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
        } else {
            imgEmpty.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            Glide.with(container.getContext()).load(item.getImgUrl()).fitCenter().placeholder(R.drawable.placeholder).crossFade().into(image);
        }
        content.setText(item.getMsgContent());
        container.addView(rootView);
        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
