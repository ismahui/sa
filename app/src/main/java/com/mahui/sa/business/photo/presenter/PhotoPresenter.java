package com.mahui.sa.business.photo.presenter;

import com.mahui.sa.business.photo.model.PhotoRepostitory;
import com.mahui.sa.business.photo.model.PhotoResponse;
import com.mahui.sa.business.photo.view.IPhotoView;
import com.mahui.sa.util.PhotoUtil;
import com.mahui.sa.util.RxApi;
import com.mahui.sa.util.StateLayout;

import java.util.List;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
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
                .create(new OnSubscribe<List<PhotoResponse>>() {
                    @Override
                    public void call(Subscriber<? super List<PhotoResponse>> subscriber) {
                        List<PhotoResponse> photoResponses = PhotoUtil.getPhotoFromLocal(mIPhotoView.getContext());
                        subscriber.onNext(photoResponses);
                        subscriber.onCompleted();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoResponse>>() {
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
                    public void onNext(List<PhotoResponse> photoResponses) {
                        if (mIPhotoView!=null){
                            if(photoResponses.size()>0){
                                mIPhotoView.changeState(StateLayout.State.ACCESS);
                                mIPhotoView.updateList(photoResponses);
                            }else {
                                mIPhotoView.changeState(StateLayout.State.EMPTY);
                            }
                        }
                    }
                });

    }

    public void getPhotoFromServer(long userId, int page){
        RxApi.execute(PhotoRepostitory.getPhotoFromServer(userId, page),new Subscriber<List<PhotoResponse>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(List<PhotoResponse> photoResponses) {
                if (photoResponses != null){
                    if (mIPhotoView != null){
                        mIPhotoView.updateList(photoResponses);
                    }
                }
            }
        });
    }
}
