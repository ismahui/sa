package com.mahui.sa.business.photo.view;

import android.content.Context;

import com.mahui.sa.business.photo.model.PhotoResponse;
import com.mahui.sa.util.StateLayout;

import java.util.List;

/**
 * Created by minghui on 2018/1/17.
 */

public interface IPhotoView {
    void updateList(List<PhotoResponse> list);
    Context getContext();
    void changeState(StateLayout.State state);
}
