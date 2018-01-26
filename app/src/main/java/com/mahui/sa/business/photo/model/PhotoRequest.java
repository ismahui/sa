package com.mahui.sa.business.photo.model;

/**
 * Created by minghui on 2018/1/26.
 */

public class PhotoRequest {
    private long mUserId;
    private int mPage;

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }
}
