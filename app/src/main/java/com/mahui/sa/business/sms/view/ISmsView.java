package com.mahui.sa.business.sms.view;

import android.content.Context;

import com.mahui.sa.business.sms.model.MessageModel;

import java.util.List;

/**
 * Created by minghui on 2018/1/16.
 */

public interface ISmsView {
    void updateList(List<MessageModel> list);
    Context getContext();
}
