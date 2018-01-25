package com.mahui.sa.business.photo.view;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.mahui.sa.R;
import com.mahui.sa.business.photo.model.PhotoModel;
import com.mahui.sa.util.BaseViewHolder;
import com.mahui.sa.util.PhotoUtil;

/**
 * Created by minghui on 2018/1/17.
 */

public class PhotoViewHolder extends BaseViewHolder {
    private ImageView mImageView;
    private CheckBox mCheckBox;
    public PhotoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object o, int position) {
        if (o instanceof PhotoModel){
            PhotoModel photoModel = (PhotoModel) o;
            PhotoUtil.loadLocalImage(mImageView,photoModel.imageUrl);
            if (photoModel.isShow){
                mCheckBox.setVisibility(View.VISIBLE);
            }else {
                mCheckBox.setVisibility(View.GONE);
            }
            mCheckBox.setChecked(photoModel.isChecked);
        }
    }

    @Override
    public void initView(View itemView) {
        mImageView = itemView.findViewById(R.id.image);
        mCheckBox = itemView.findViewById(R.id.choose);
    }
}
