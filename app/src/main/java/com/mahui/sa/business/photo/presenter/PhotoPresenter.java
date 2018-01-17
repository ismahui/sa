package com.mahui.sa.business.photo.presenter;

import com.mahui.sa.business.photo.view.IPhotoView;
import com.mahui.sa.util.PhotoUtil;

/**
 * Created by minghui on 2018/1/17.
 */

public class PhotoPresenter {
    private IPhotoView mIPhotoView;
    public PhotoPresenter(IPhotoView iPhotoView){
        mIPhotoView = iPhotoView;
    }

    public void getPhotoFromLocal(){
        mIPhotoView.updateList(PhotoUtil.getPhotoFromLocal(mIPhotoView.getContext()));
    }
}
