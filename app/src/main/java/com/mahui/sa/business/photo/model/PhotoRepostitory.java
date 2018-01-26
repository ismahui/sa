package com.mahui.sa.business.photo.model;

import com.google.gson.Gson;
import com.mahui.sa.util.OkHttpManager;

import net.sf.json.JSONArray;


import java.io.IOException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by minghui on 2018/1/26.
 */

public class PhotoRepostitory {
    private static String URL_GET_PHOT_FROME_SERVER = "";
    public static Observable getPhotoFromServer(long userId , int page){
        final PhotoRequest photoRequest = new PhotoRequest();
        photoRequest.setUserId(userId);
        photoRequest.setPage(page);
        final Gson gson = new Gson();
        return Observable.create(new Observable.OnSubscribe<List<PhotoResponse>>() {
            @Override
            public void call(Subscriber<? super List<PhotoResponse>> subscriber) {
                try {
                    String datas= OkHttpManager.getInstance().post(URL_GET_PHOT_FROME_SERVER,gson.toJson(photoRequest));
                    List<PhotoResponse> photoResponses = (List<PhotoResponse>) JSONArray.toArray(JSONArray.fromObject(datas),PhotoResponse.class);
                    subscriber.onNext(photoResponses);
                    subscriber.onCompleted();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
