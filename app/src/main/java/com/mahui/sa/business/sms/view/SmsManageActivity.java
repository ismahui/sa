package com.mahui.sa.business.sms.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mahui.sa.R;
import com.mahui.sa.business.sms.model.MessageModel;
import com.mahui.sa.business.sms.presenter.SmsPresenter;
import com.mahui.sa.util.StateLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minghui on 2018/1/16.
 */

public class SmsManageActivity extends Activity implements ISmsView{
    private RecyclerView mSmsListView;
    private StateLayout mStateLayout;
    private SmsListViewAdapter mSmsListViewAdapter;
    private List<MessageModel> mMessageModels = new ArrayList<>();
    private SmsPresenter mSmsPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        initView();

    }

    public void initView(){
        mSmsListView = findViewById(R.id.sms_list);
        mStateLayout = findViewById(R.id.state_layout);
        mStateLayout.setState(StateLayout.State.LOADING);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mSmsListView.setLayoutManager(layoutManager);
        mSmsListViewAdapter = new SmsListViewAdapter(mMessageModels,this);
        mSmsListView.setAdapter(mSmsListViewAdapter);
        mSmsPresenter = new SmsPresenter(this);
        mSmsPresenter.initData();
    }

    @Override
    public void updateList(List<MessageModel> list) {
        if (list==null || list.isEmpty()){
            mStateLayout.changeState(StateLayout.State.EMPTY);
        } else {
            mMessageModels.addAll(list);
            mStateLayout.changeState(StateLayout.State.ACCESS);
            mSmsListViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }




    private static class SmsListViewAdapter extends Adapter{
        private List<MessageModel> mData;
        private Context mContext;
        public SmsListViewAdapter(List<MessageModel> data ,Context context){
            mData = data;
            mContext = context;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.sms_list_item,parent,false);
            SmsViewHolder smsViewHolder = new SmsViewHolder(view);
            return smsViewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof SmsViewHolder){
                ((SmsViewHolder) holder).bindData(mData.get(position),position);
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }
}
