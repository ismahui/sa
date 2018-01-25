package com.mahui.sa.business.photo.presenter;

import com.mahui.sa.business.photo.model.PhotoModel;
import com.mahui.sa.business.photo.view.IPhotoView;
import com.mahui.sa.util.PhotoUtil;
import com.mahui.sa.util.StateLayout;

import java.util.List;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by minghui on 2018/1/17.
 */

public class PhotoPresenter {
    private IPhotoView mIPhotoView;

    public PhotoPresenter(IPhotoView iPhotoView) {
        mIPhotoView = iPhotoView;
    }

    public void getPhotoFromLocal() {
        Observable
                .create(new OnSubscribe<List<PhotoModel>>() {
                    @Override
                    public void call(Subscriber<? super List<PhotoModel>> subscriber) {
                        List<PhotoModel> photoModels = PhotoUtil.getPhotoFromLocal(mIPhotoView.getContext());
                        subscriber.onNext(photoModels);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        if (mIPhotoView!=null){
                            mIPhotoView.changeState(StateLayout.State.ERROR);
                        }
                    }

                    @Override
                    public void onNext(List<PhotoModel> photoModels) {
                        if (mIPhotoView!=null){
                            if(photoModels.size()>0){
                                mIPhotoView.changeState(StateLayout.State.ACCESS);
                                mIPhotoView.updateList(photoModels);
                            }else {
                                mIPhotoView.changeState(StateLayout.State.EMPTY);
                            }
                        }
                    }
                });

    }
}
