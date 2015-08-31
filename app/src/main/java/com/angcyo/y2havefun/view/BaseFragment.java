package com.angcyo.y2havefun.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angcyo.y2havefun.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by angcyo on 15-08-31-031.
 */
public abstract class BaseFragment extends Fragment {

    @Bind(R.id.recycle)
    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    protected abstract void loadData();

    protected abstract void initView();

    protected void initAfter() {
    }
}
