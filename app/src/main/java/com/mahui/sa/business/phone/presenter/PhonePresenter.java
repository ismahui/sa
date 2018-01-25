package com.mahui.sa.business.phone.presenter;


import com.mahui.sa.business.phone.model.PhoneModel;
import com.mahui.sa.business.phone.view.IPhoneView;
import com.mahui.sa.util.PageConfig;
import com.mahui.sa.util.PhoneUtil;
import com.mahui.sa.util.StateLayout;

import java.util.List;

import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by minghui on 2018/1/23.
 */

public class PhonePresenter {

    private IPhoneView mIPhoneView;

    public PhonePresenter(IPhoneView iPhoneView){
        mIPhoneView = iPhoneView;
    }

    public void getPhoneFromeLocal(final int currentPage){
        rx.Observable
                .create(new OnSubscribe<List<PhoneModel>>() {
                    @Override
                    public void call(Subscriber<? super List<PhoneModel>> subscriber) {
                        List<PhoneModel> phoneModels = PhoneUtil.readPhoneNumberFromLocal(mIPhoneView.getContext(),(currentPage-1)* PageConfig.PAGE_SIZE);
                        subscriber.onNext(phoneModels);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhoneModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if(mIPhoneView!=null){
                            mIPhoneView.changeState(StateLayout.State.ERROR);
                        }
                    }

                    @Override
                    public void onNext(List<PhoneModel> phoneModels) {
                        if (mIPhoneView!=null){
                            if (phoneModels.size()>0){
                                mIPhoneView.changeState(StateLayout.State.ACCESS);
                                mIPhoneView.updateList(phoneModels);
                            }
                        }
                    }

                });
    }

}
