package com.mahui.sa.business.photo.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mahui.sa.R;
import com.mahui.sa.business.photo.model.PhotoModel;
import com.mahui.sa.business.photo.presenter.PhotoPresenter;
import com.mahui.sa.util.BaseFragment;
import com.mahui.sa.util.RecyclerItemClickListener;
import com.mahui.sa.util.StateLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mahui on 2018/1/18.
 */

public class LocalPhotoFragment extends BaseFragment implements IPhotoView ,View.OnClickListener{

    private RecyclerView mPhotoListView;
    private StateLayout mStateLayout;
    private PhotoListViewAdapter mPhotoListViewAdapter;
    private PhotoPresenter mPhotoPresenter;
    private List<PhotoModel> mPhotoModels = new ArrayList<>();
    private List<String> fileUrl = new ArrayList<>();
    private Button mNotChooseAll;
    private Button mChooseAll;
    private Button mUpload;

    @Override
    public View onContentViewInit(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_local_photo,null,false);
    }

    @Override
    public void initView(View rootView){
        mPhotoListView = rootView.findViewById(R.id.photo_list);
        mStateLayout = rootView.findViewById(R.id.state_layout);
        mNotChooseAll = rootView.findViewById(R.id.not_choose);
        mChooseAll = rootView.findViewById(R.id.choose_all);
        mUpload = rootView.findViewById(R.id.upload);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),4);
        mPhotoListView.setLayoutManager(gridLayoutManager);
        mPhotoListViewAdapter = new PhotoListViewAdapter(mPhotoModels,this.getContext());
        mPhotoListView.setAdapter(mPhotoListViewAdapter);
        mPhotoPresenter = new PhotoPresenter(this);
        changeState(StateLayout.State.LOADING);

    }

    @Override
    public void initData() {
        mStateLayout.changeState(StateLayout.State.LOADING);
        mPhotoPresenter.getPhotoFromLocal();
    }

    @Override
    public void initListener() {
        mPhotoListView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), mPhotoListView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PhotoModel photoModel = mPhotoModels.get(position);
                PhotoDeatilDialog photoDeatilDialog = new PhotoDeatilDialog(LocalPhotoFragment.this.getActivity(),photoModel.imageUrl, R.style.MyDialog);
                photoDeatilDialog.setAttributes(getActivity().getWindowManager());
                photoDeatilDialog.show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                showCheckBox(position);
            }
        }));
        mChooseAll.setOnClickListener(this);
        mNotChooseAll.setOnClickListener(this);
        mUpload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
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
        return getActivity().getBaseContext();
    }

    @Override
    public void changeState(StateLayout.State state) {
        mStateLayout.changeState(state);
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
