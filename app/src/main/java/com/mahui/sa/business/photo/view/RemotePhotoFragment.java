package com.mahui.sa.business.photo.view;

import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.mahui.sa.R;
import com.mahui.sa.business.photo.model.PhotoResponse;
import com.mahui.sa.business.photo.presenter.PhotoPresenter;
import com.mahui.sa.util.BaseFragment;
import com.mahui.sa.util.PagingRecycleView;
import com.mahui.sa.util.StateLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahui on 2018/1/18.
 */

public class RemotePhotoFragment extends BaseFragment implements IPhotoView {
    private StateLayout mStateLayout;
    private PagingRecycleView mPhotoListView;
    private PhotoPresenter mPhotoPresenter;
    private LocalPhotoFragment.PhotoListViewAdapter mPhotoListViewAdapter;
    private List<PhotoResponse> mPhotoResponses = new ArrayList<>();
    @Override
    public View onContentViewInit(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_remote_photo,null,false);
    }

    @Override
    public void initView(View rootView) {
        mStateLayout = rootView.findViewById(R.id.state_layout);
        mPhotoListView = rootView.findViewById(R.id.photo_list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),4);
        mPhotoListView.setLayoutManager(gridLayoutManager);
        mPhotoListViewAdapter = new LocalPhotoFragment.PhotoListViewAdapter(mPhotoResponses,this.getContext());
        mPhotoListView.setAdapter(mPhotoListViewAdapter);
        mPhotoPresenter = new PhotoPresenter(this);
        changeState(StateLayout.State.LOADING);
    }

    @Override
    public void initData() {
        mPhotoPresenter.getPhotoFromServer(11,1);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void updateList(List<PhotoResponse> list) {
        if (mPhotoListViewAdapter.getItemCount() == 0 && (list == null || list.isEmpty())){
            return;
        }
        changeState(StateLayout.State.ACCESS);
        mPhotoResponses.addAll(list);
        mPhotoListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void changeState(StateLayout.State state) {
        mStateLayout.changeState(state);
    }

    @Override
    public boolean isNeedLazyLoad() {
        return true;
    }
}
