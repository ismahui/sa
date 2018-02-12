package com.mahui.sa.business.photo.model;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
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
                    //String datas= OkHttpManager.getInstance().post(URL_GET_PHOT_FROME_SERVER,gson.toJson(photoRequest));
                    List<PhotoResponse> list = new ArrayList<>();
                    for (int i=0;i<3;i++){
                        PhotoResponse photoResponse = new PhotoResponse();
                        photoResponse.imageUrl = "http://pic.sc.chinaz.com/files/pic/pic9/201410/apic6568.jpg";
                        photoResponse.isNative = false;
                        list.add(photoResponse);
                    }
                    String datas = gson.toJson(list);

                    List<PhotoResponse> photoResponses = (List<PhotoResponse>) gson.fromJson(datas, new TypeToken<List<PhotoResponse>>() {}.getType());
                    subscriber.onNext(photoResponses);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
