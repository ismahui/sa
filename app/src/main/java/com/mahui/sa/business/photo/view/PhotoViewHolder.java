package com.mahui.sa.business.photo.view;

import android.view.View;
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
    public PhotoViewHolder(View itemView) {
        super(itemView);
        mImageView = itemView.findViewById(R.id.image);
    }

    @Override
    public void bindData(Object o, int position) {
        if (o instanceof PhotoModel){
            PhotoModel photoModel = (PhotoModel) o;
            mImageView.setImageBitmap(PhotoUtil.getBitmap(photoModel.imageUrl));
        }
    }
}
