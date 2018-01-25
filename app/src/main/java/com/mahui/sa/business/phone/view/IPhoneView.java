package com.mahui.sa.business.phone.view;

import android.content.Context;

import com.mahui.sa.business.phone.model.PhoneModel;
import com.mahui.sa.util.StateLayout;

import java.util.List;

/**
 * Created by minghui on 2018/1/23.
 */

public interface IPhoneView {
    void updateList(List<PhoneModel> phoneModelList);
    Context getContext();
    void  changeState(StateLayout.State state);
}
