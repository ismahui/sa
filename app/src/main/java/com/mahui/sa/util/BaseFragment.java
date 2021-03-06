package com.mahui.sa.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mahui on 2018/1/18.
 */

public abstract class BaseFragment extends Fragment {
    private boolean isFirstLoad = true;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onContentViewInit(inflater);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        if (!isNeedLazyLoad()){
            initData();
        }
        initListener();
    }

    public abstract View onContentViewInit(LayoutInflater layoutInflater);

    public abstract void initView(View rootView);

    public abstract void initListener();

    public abstract void initData();

    public  boolean isNeedLazyLoad(){
        return false;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            if (isNeedLazyLoad()&&isFirstLoad){
                isFirstLoad = false;
                initData();
            }
        }
    }
}
