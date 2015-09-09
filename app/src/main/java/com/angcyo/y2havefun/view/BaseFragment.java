package com.angcyo.y2havefun.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angcyo.y2havefun.components.RDataService;
import com.angcyo.y2havefun.mode.HandlerTask;
import com.angcyo.y2havefun.util.Logger;

import butterknife.ButterKnife;

/**
 * Created by angcyo on 15-08-31-031.
 */
public abstract class BaseFragment extends Fragment {
    protected Handler handler;
    protected int DELAY_SHOW_TIME = 300;
    private Runnable delayRunnable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler(Looper.getMainLooper());
        delayRunnable = new Runnable() {
            @Override
            public void run() {
                onDelayShow();
            }
        };

        loadData(savedInstanceState);
        Logger.e("onCreate " + hashCode());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = loadView(inflater, container, savedInstanceState);
        initView(view);
        initAfter();

        Logger.e("onCreateView " + hashCode());
        return view;
    }

    protected void loadData(Bundle savedInstanceState) {

    }

    protected abstract View loadView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void initView(View rootView);

    protected void initAfter() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Logger.e("onDestroyView " + hashCode());

    }

    protected void refreshData(HandlerTask task) {
        RDataService.addTask(task);//需要先添加任务,才会执行任务
        Intent intent = new Intent(getActivity(), RDataService.class);
        intent.putExtra(RDataService.KEY_DATA_TYPE, task.taskType);
        intent.putExtra(RDataService.KEY_DATA_UPDATE_OR_LOADING, task.taskLoadType);
        intent.putExtra(RDataService.KEY_DATA_URL, task.url);
        getActivity().startService(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Logger.e("onAttach " + hashCode());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logger.e("onDetach " + hashCode());

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        try {
            if (isVisibleToUser) {
                onShow();
                handler.postDelayed(delayRunnable, DELAY_SHOW_TIME);
            } else {
                onHide();
                handler.removeCallbacks(delayRunnable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDelayShow() {

    }

    protected void onShow() {

    }

    protected void onHide() {

    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.e("onStart " + hashCode());

    }

    @Override
    public void onResume() {
        super.onResume();
        Logger.e("onResume " + hashCode());
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.e("onPause " + hashCode());
    }

    @Override
    public void onStop() {
        super.onStop();
        Logger.e("onStop " + hashCode());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logger.e("onViewCreated " + hashCode());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.e("onActivityCreated " + hashCode());
    }
}
