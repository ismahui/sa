package com.mahui.sa.business.sms.presenter;

import com.mahui.sa.business.sms.model.MessageModel;
import com.mahui.sa.business.sms.view.ISmsView;
import com.mahui.sa.util.SmsUtil;

import java.util.List;

/**
 * Created by minghui on 2018/1/16.
 */

public class SmsPresenter {
    private ISmsView mISmsView;
    public SmsPresenter(ISmsView smsView){
       mISmsView = smsView;
    }

    public void initData(){
        List<MessageModel> list= SmsUtil.getMessage(mISmsView.getContext());
        mISmsView.updateList(list);
    }
}
