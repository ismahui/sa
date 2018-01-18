package com.mahui.sa.business.photo.view;

import android.view.LayoutInflater;
import android.view.View;

import com.mahui.sa.R;
import com.mahui.sa.util.BaseFragment;

/**
 * Created by mahui on 2018/1/18.
 */

public class RemotePhotoFragment extends BaseFragment {
    @Override
    public View onContentViewInit(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_local_photo,null,false);
    }

    @Override
    public void initView(View rootView) {

    }

    @Override
    public void initListener() {

    }
}
