package com.mahui.sa.business.photo.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.mahui.sa.R;
import com.mahui.sa.business.photo.model.PhotoModel;
import com.mahui.sa.business.photo.presenter.PhotoPresenter;
import com.mahui.sa.util.BaseActivity;
import com.mahui.sa.util.RecyclerItemClickListener;
import com.mahui.sa.util.StateLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minghui on 2018/1/17.
 */

public class PhotoActivity extends BaseActivity implements IPhotoView{
    private RecyclerView mPhotoListView;
    private StateLayout mStateLayout;
    private PhotoListViewAdapter mPhotoListViewAdapter;
    private PhotoPresenter mPhotoPresenter;
    private List<PhotoModel> mPhotoModels = new ArrayList<>();
    private List<String> fileUrl = new ArrayList<>();
    private Button mNotChooseAll;
    private Button mChooseAll;
    private Button mUpload;
    private Button mDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void initView(){
        super.initView();
        mPhotoListView = findViewById(R.id.photo_list);
        mStateLayout = findViewById(R.id.state_layout);
        mNotChooseAll = findViewById(R.id.not_choose);
        mChooseAll = findViewById(R.id.choose_all);
        mUpload = findViewById(R.id.upload);
        mDownload = findViewById(R.id.download);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);
        mPhotoListView.setLayoutManager(gridLayoutManager);
        mPhotoListViewAdapter = new PhotoListViewAdapter(mPhotoModels,this);
        mPhotoListView.setAdapter(mPhotoListViewAdapter);
        mPhotoPresenter = new PhotoPresenter(this);
        mStateLayout.changeState(StateLayout.State.LOADING);
        mPhotoPresenter.getPhotoFromLocal();

    }

    @Override
    protected void initListener() {
        mPhotoListView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mPhotoListView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // ...
                Toast.makeText(getContext(),"这是点击事件",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                showCheckBox(position);
            }
        }));
        mChooseAll.setOnClickListener(this);
        mNotChooseAll.setOnClickListener(this);
        mUpload.setOnClickListener(this);
        mDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        switch (id){
            case R.id.not_choose:
                showAllNotChecked();
                break;
            case R.id.choose_all:
                showAllChecked();
                break;
            case R.id.upload:
                break;
            case R.id.download:
                break;
        }
    }

    public void showCheckBox(int position){
        for (int i=0;i<mPhotoModels.size();i++){
            PhotoModel photoModel =mPhotoModels.get(i);
            photoModel.isShow = true;
            if (i==position){
                photoModel.isChecked = true;
                fileUrl.add(photoModel.imageUrl);
            }
        }
        mPhotoListViewAdapter.notifyDataSetChanged();
    }

    public void showAllChecked(){
        if (!fileUrl.isEmpty()){
            fileUrl.clear();
        }
        for (int i=0;i<mPhotoModels.size();i++){
            PhotoModel photoModel =mPhotoModels.get(i);
            photoModel.isShow = true;
            photoModel.isChecked = true;
            fileUrl.add(photoModel.imageUrl);
        }
        mPhotoListViewAdapter.notifyDataSetChanged();
    }

    public void showAllNotChecked(){
        if (!fileUrl.isEmpty()){
            fileUrl.clear();
        }
        for (int i=0;i<mPhotoModels.size();i++){
            PhotoModel photoModel =mPhotoModels.get(i);
            photoModel.isShow = true;
            photoModel.isChecked = false;
        }
        if (!fileUrl.isEmpty()){
            fileUrl.clear();
        }
        mPhotoListViewAdapter.notifyDataSetChanged();
    }

    public void hideCheckBox(){
        for (int i=0;i<mPhotoModels.size();i++){
            PhotoModel photoModel =mPhotoModels.get(i);
            photoModel.isShow = true;
            photoModel.isChecked = false;
        }
    }


    @Override
    public View onContentViewInit(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.activity_photo,null,false);
    }

    @Override
    public String initActionBarTitle() {
        return "图片管理";
    }

    @Override
    public void onActionBarViewCreated() {
        super.onActionBarViewCreated();
    }

    @Override
    public void onActionBarViewClick() {
        super.onActionBarViewClick();
    }

    @Override
    public void updateList(List<PhotoModel> list) {
        if (list==null || list.isEmpty()){
            mStateLayout.changeState(StateLayout.State.EMPTY);
        } else {
            mPhotoModels.addAll(list);
            mPhotoModels.addAll(list);
            mPhotoModels.addAll(list);
            mStateLayout.changeState(StateLayout.State.ACCESS);
            mPhotoListViewAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }


    private static class PhotoListViewAdapter extends RecyclerView.Adapter{
        private List<PhotoModel> mData;
        private Context mContext;
        public PhotoListViewAdapter(List<PhotoModel> data ,Context context){
            mData = data;
            mContext = context;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.photo_list_item,parent,false);
            return new PhotoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof PhotoViewHolder){
                ((PhotoViewHolder) holder).bindData(mData.get(position),position);
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
    }


}
