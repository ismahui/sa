package com.mahui.sa.util;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by mahui on 2018/1/19.
 */

public class RxApi {

    public static void get(Subscriber<Object> subscriber) {
        Observable
                .create(new Observable.OnSubscribe<Object>() {
                    @Override
                    public void call(Subscriber<? super Object> subscriber) {
                        try {
                            subscriber.onNext(OkHttpManager.getInstance().get(""));
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static void post(Observable observable, Subscriber subscriber) {

    }
}
