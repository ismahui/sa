package com.mahui.sa.business.photo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.mahui.sa.R;
import com.mahui.sa.util.BaseDialog;
import com.mahui.sa.util.PhotoUtil;

/**
 * Created by mahui on 2018/1/25.
 */

public class PhotoDeatilDialog extends BaseDialog implements View.OnClickListener{
    private ImageView mImageView;
    private ImageView mCancer;
    private String mUrl;
    public PhotoDeatilDialog(@NonNull Context context ,String url ,int styleId) {
        super(context,styleId);
        mUrl = url;
        initData();
    }
    @Override
    public View onContetViewInit(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.dialog_photo_detail,null,false);
    }

    @Override
    public void initView(View view) {
        mImageView = view.findViewById(R.id.image_detail);
        mCancer = view.findViewById(R.id.cancel);
    }

    @Override
    public void initData() {
        PhotoUtil.loadImageFromLocal(mImageView,mUrl);
    }

    @Override
    public void initListener() {
        mCancer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.cancel:
                this.dismiss();
                break;
        }
    }
}
