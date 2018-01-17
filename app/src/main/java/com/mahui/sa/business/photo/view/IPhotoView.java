package com.mahui.sa.business.photo.view;

import android.content.Context;

import com.mahui.sa.business.photo.model.PhotoModel;

import java.util.List;

/**
 * Created by minghui on 2018/1/17.
 */

public interface IPhotoView {
    void updateList(List<PhotoModel> list);
    Context getContext();
}
