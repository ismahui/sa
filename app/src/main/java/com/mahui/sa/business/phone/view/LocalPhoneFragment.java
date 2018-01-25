package com.mahui.sa.business.phone.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahui.sa.R;
import com.mahui.sa.business.phone.model.PhoneModel;
import com.mahui.sa.business.phone.presenter.PhonePresenter;
import com.mahui.sa.util.BaseFragment;
import com.mahui.sa.util.PagingRecycleView;
import com.mahui.sa.util.StateLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minghui on 2018/1/23.
 */

public class LocalPhoneFragment extends BaseFragment implements IPhoneView ,PagingRecycleView.IPageLoad {

    private PagingRecycleView mRecyclerView;
    private PhoneAdapter mPhoneAdapter;
    private StateLayout mStateLayout;
    private List<PhoneModel> mPhoneModels = new ArrayList<>();
    private PhonePresenter mPhonePresenter = new PhonePresenter(this);

    @Override
    public View onContentViewInit(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_local_phone,null,false);
    }

    @Override
    public void initView(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.phone_list);
        mStateLayout = rootView.findViewById(R.id.state_layout);
        mPhoneAdapter = new PhoneAdapter(mPhoneModels,getActivity());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mPhoneAdapter);
        mRecyclerView.setIPageLoad(this);
        changeState(StateLayout.State.LOADING);
    }

    @Override
    public void initData() {
        mPhonePresenter.getPhoneFromeLocal(1);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void changeState(StateLayout.State state) {
        mStateLayout.changeState(state);
    }

    @Override
    public void updateList(List<PhoneModel> phoneModelList) {
        if (mPhoneAdapter.getItemCount()==0 && (phoneModelList==null || phoneModelList.isEmpty())){
            mStateLayout.changeState(StateLayout.State.EMPTY);
        } else {
            mPhoneModels.addAll(phoneModelList);
            mStateLayout.changeState(StateLayout.State.ACCESS);
            mPhoneAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public Context getContext() {
        return getActivity().getBaseContext();
    }

    @Override
    public void loadNextPage(int page) {
        mPhonePresenter.getPhoneFromeLocal(page);
    }

    private static class PhoneAdapter extends RecyclerView.Adapter {
        private List<PhoneModel> mPhoneModels;
        private Context mContext;

        public PhoneAdapter(List<PhoneModel> phoneModels, Context context) {
            mPhoneModels = phoneModels;
            mContext = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.phone_list_item, parent, false);
            return new PhoneViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof PhoneViewHolder) {
                ((PhoneViewHolder) holder).bindData(mPhoneModels.get(position), position);
            }
        }

        @Override
        public int getItemCount() {
            return mPhoneModels.size();
        }
    }

}
