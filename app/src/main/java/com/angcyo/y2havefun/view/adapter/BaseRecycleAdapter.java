package com.angcyo.y2havefun.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.angcyo.y2havefun.R;
import com.angcyo.y2havefun.components.RDataService;
import com.angcyo.y2havefun.mode.ContentItem;
import com.angcyo.y2havefun.mode.event.MainAdapterEvent;
import com.angcyo.y2havefun.util.Util;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by angcyo on 15-08-31-031.
 */
public class BaseRecycleAdapter extends RecyclerView.Adapter<BaseRecycleAdapter.BaseRecycleViewHolder> {

    public static final int ITEM_TYPE_LOADING = 1;
    public static final int ITEM_TYPE_NORMAL = 0;

    private List<ContentItem> datas;
    private Context context;
    private String loadTip = "";

    public BaseRecycleAdapter(Context content) {
        datas = new ArrayList<>();
        this.context = content;
    }

    public BaseRecycleAdapter(Context context, List<ContentItem> datas) {
        this.datas = datas;
        this.context = context;
    }

    public void setLoadTip(String tip) {
        loadTip = tip;
//        notifyDataSetChanged();
        notifyItemChanged(datas.size());
    }

    @Override
    public int getItemViewType(int position) {
        if (isLastItem(position)) {//最后一项, 显示为加载更多
            return ITEM_TYPE_LOADING;
        }
        return ITEM_TYPE_NORMAL;
    }

    @Override
    public BaseRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = parent.inflate(parent.getContext(), R.layout.adapter_main_item, null);
        return new BaseRecycleViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewHolder holder, final int position) {
        if (isLastItem(position)) {
            holder.contentLayout.setVisibility(View.GONE);
            holder.loadLayout.setVisibility(View.VISIBLE);
            holder.loadTip.setText("" + (Util.isEmpty(loadTip) ? context.getString(R.string.want_load_more) : loadTip));
        } else {
            holder.contentLayout.setVisibility(View.VISIBLE);
            holder.loadLayout.setVisibility(View.GONE);
            ContentItem item = datas.get(position);
            holder.content.setText(item.getMsgContent() + "");
            if (!Util.isEmpty(item.getImgUrl())) {
                holder.image.setVisibility(View.VISIBLE);
                Glide.with(context).load(item.getImgUrl()).placeholder(R.drawable.placeholder).crossFade().into(holder.image);
                holder.image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainAdapterEvent event = new MainAdapterEvent();
                        event.clickPosition = position;
                        EventBus.getDefault().post(event);
                    }
                });
            } else {
                holder.image.setVisibility(View.GONE);
            }

        }
    }

    private boolean isLastItem(int position) {
        if (getItemCount() >= 5 && (position + 1) >= getItemCount()) {//最后一项, 显示为加载更多
            return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        if (datas == null || datas.size() == 0) {
            return 0;
        }
        return datas.size() + 1;
    }

//    public void addDatas(List<ContentItem> datas) {
//        this.datas.addAll(datas);
//        notifyDataSetChanged();
//    }

    public void setDatas(List<ContentItem> datas, int loadType, int itemCount) {
//        notifyDataSetChanged();
        this.datas = datas;
        if (loadType == RDataService.DATA_UPDATE) {
            notifyDataSetChanged();
        } else {
            notifyItemRangeInserted(datas.size() - itemCount, itemCount);
        }
    }

    static class BaseRecycleViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.content_layout)
        RelativeLayout contentLayout;
        @Bind(R.id.load_bar)
        ProgressBar loadBar;
        @Bind(R.id.load_layout)
        RelativeLayout loadLayout;
        @Bind(R.id.card)
        CardView card;
        @Bind(R.id.load_tip)
        TextView loadTip;

        public BaseRecycleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
