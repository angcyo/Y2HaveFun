package com.angcyo.y2havefun.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.angcyo.y2havefun.DetailsActivity;
import com.angcyo.y2havefun.R;
import com.angcyo.y2havefun.components.RDataService;
import com.angcyo.y2havefun.control.DataControl;
import com.angcyo.y2havefun.control.HttpAsync;
import com.angcyo.y2havefun.mode.HandlerTask;
import com.angcyo.y2havefun.mode.event.EventUpdate;
import com.angcyo.y2havefun.mode.event.MainAdapterEvent;
import com.angcyo.y2havefun.util.PopupTipWindow;
import com.angcyo.y2havefun.util.Util;
import com.angcyo.y2havefun.view.adapter.BaseRecycleAdapter;
import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Created by angcyo on 15-08-31-031.
 */
public class CommonFragment extends BaseFragment {
    HttpAsync httpAsync;
    @Bind(R.id.recycle)
    RecyclerView recycle;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;
    @Bind(R.id.temp_tip)
    TextView tempTip;

    int position = 0;
    private BaseRecycleAdapter adapter;

    private int scrollState = RecyclerView.SCROLL_STATE_IDLE;//保存recycle 滚动的状态
    private boolean isLastItem = false;//是否滚动到了最后一个item

    private boolean isRefreshing = false;//是否正在刷新
    private boolean isLoadingMore = false;//是否正在加载更多

    @Override
    protected void loadData(Bundle savedInstanceState) {
        super.loadData(savedInstanceState);
        position = getArguments().getInt(RDataService.KEY_DATA_TYPE);
    }

    @Override
    protected View loadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
        adapter = new BaseRecycleAdapter(getActivity(), DataControl.getData(position));
        recycle.setAdapter(adapter);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recycle.setItemAnimator(new DefaultItemAnimator());
        recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                scrollState = newState;
                onLoadMore(scrollState, isLastItem);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (manager.findLastVisibleItemPosition() >= manager.getItemCount() - 1) {
                    isLastItem = true;
                } else {
                    isLastItem = false;
                }
            }
        });

        refresh.setColorSchemeResources(android.support.design.R.color.accent_material_light);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CommonFragment.this.onRefresh();
            }
        });

        if (DataControl.getData(position).size() > 0) {
            tempTip.setVisibility(View.GONE);
        } else {
            tempTip.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser && refresh != null) {
//            refresh.setRefreshing(true);
//        }
    }

    @Override
    protected void onHide() {//Fragment 不可见的时候调用
        super.onHide();
        EventBus.getDefault().unregister(this);
        Glide.get(getActivity()).clearMemory();

        HandlerTask task = createTask(RDataService.DATA_UPDATE);
        RDataService.removeTask(task);
        if (refresh != null) {
            refresh.setRefreshing(false);
        }
    }

    @Override
    protected void onShow() {//Fragment 可见的时候调用
        super.onShow();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDelayShow() {//Fragment 可见的时候, 延迟调用,解决在Viewpager里面 ,第一个 fragment 无法显示 刷新布局的bug
        super.onDelayShow();

        if (checkNet()) {
            HandlerTask task = createTask(RDataService.DATA_UPDATE);
            if (!RDataService.isExistTask(task) && !RDataService.isLoadedTask(task)) {
                refreshData(task);
                refresh.setRefreshing(true);
                isRefreshing = true;
            }
        }
    }

    protected boolean checkNet() {
        if (Util.isNetOk(getActivity())) {
            return true;
        }
        PopupTipWindow.showTip(getActivity(), PopupTipWindow.ICO_TYPE_FAILED, "客官,蜘蛛没网不能生存呀!");
        return false;
    }

    protected void onRefresh() {//SwipeRefreshLayout 请求刷新的时候调用
        if (!checkNet()) {
            refresh.setRefreshing(false);
            isRefreshing = true;
            return;
        }

        if (isRefreshing) {
            PopupTipWindow.showTip(getActivity(), "客官,慢点操作...");
        } else {
            HandlerTask task = createTask(RDataService.DATA_UPDATE);
            if (!RDataService.isExistTask(task)) {
                refreshData(task);
                isRefreshing = true;
            }
        }
    }

    private void onLoadMore(int scrollState, boolean isLastItem) {
        if (isLoadingMore) {
            return;
        }
        if (isLastItem) {
            if (scrollState == RecyclerView.SCROLL_STATE_IDLE) {
                adapter.setLoadTip(getString(R.string.text_temp));
                if (checkNet()) {
                    HandlerTask task = createTask(RDataService.DATA_LOAD_MORE);
                    if (!RDataService.isLoadedTask(task)) {
                        refreshData(task);
                        isLoadingMore = true;
                    } else {
                        isLoadingMore = false;
                    }
                } else {
                    isLoadingMore = false;
                    adapter.setLoadTip(getString(R.string.want_network));
                }
            } else {
                adapter.setLoadTip(getString(R.string.want_load_more));
            }
        }
    }

    protected HandlerTask createTask(int taskLoadType) {
        HandlerTask task = new HandlerTask();
        task.taskType = position;
        task.taskLoadType = taskLoadType;
        task.url = DataControl.getUrl(taskLoadType, position);
        return task;
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onEvent(EventUpdate event) {
        if (event.type != position) {
            return;
        }
        if (event.loadType == RDataService.DATA_UPDATE) {
            isRefreshing = false;
            if (refresh != null) {
                refresh.setRefreshing(false);
            }
            if (DataControl.getData(position).size() > 0) {
                tempTip.setVisibility(View.GONE);
            }
        } else {
            isLoadingMore = false;
            String tip;
            if (event.datas.size() > 0) {
                tip = "小二批发到:" + event.datas.size() + "袋辣条";
            } else {
                tip = "小二卷款失踪了!";
            }
            PopupTipWindow.showTip(getActivity(), tip);
            DataControl.increment(event.type);
        }
        adapter.setDatas(DataControl.getData(position), event.loadType, event.datas.size());
        if (event.loadType == RDataService.DATA_UPDATE && event.datas.size() > 0) {
            recycle.smoothScrollToPosition(0);
        }
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onClickEvent(MainAdapterEvent event) {
        launchActivity(DetailsActivity.class, position, event.clickPosition);
    }

    protected void launchActivity(Class targetClass, int type, int position) {
        Intent intent = new Intent(getActivity(), targetClass);
        intent.putExtra(RDataService.KEY_DATA_TYPE, type);
        intent.putExtra(DetailsActivity.KEY_POSITION, position);
        getActivity().startActivity(intent);
    }
}

