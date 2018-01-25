package com.mahui.sa.business.sms.presenter;


import com.mahui.sa.business.sms.model.MessageModel;
import com.mahui.sa.business.sms.view.ISmsView;
import com.mahui.sa.util.SmsUtil;
import com.mahui.sa.util.StateLayout;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static rx.Observable.*;

/**
 * Created by minghui on 2018/1/16.
 */

public class SmsPresenter {
    private ISmsView mISmsView;
    public SmsPresenter(ISmsView smsView){
       mISmsView = smsView;
    }

    public void readMessage(){
        Observable
                .create(new OnSubscribe<List<MessageModel>>() {
                    @Override
                    public void call(Subscriber<? super List<MessageModel>> subscriber) {
                        List<MessageModel> messageModels =SmsUtil.readMessage(mISmsView.getContext());
                        subscriber.onNext(messageModels);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MessageModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (mISmsView!=null){
                            mISmsView.changeState(StateLayout.State.ERROR);
                        }
                    }

                    @Override
                    public void onNext(List<MessageModel> messageModels) {
                        if (mISmsView!=null){
                            if(messageModels.size()>0){
                                mISmsView.changeState(StateLayout.State.ACCESS);
                                mISmsView.updateList(messageModels);
                            }else {
                                mISmsView.changeState(StateLayout.State.EMPTY);
                            }
                        }
                    }
                });
    }

    public void writeMessage(List<MessageModel> list){
        SmsUtil.writeMessage(mISmsView.getContext(),list);
    }
}
