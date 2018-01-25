package com.mahui.sa.business.phone.view;

import android.view.View;
import android.widget.TextView;

import com.mahui.sa.R;
import com.mahui.sa.business.phone.model.PhoneModel;
import com.mahui.sa.util.BaseViewHolder;

/**
 * Created by minghui on 2018/1/23.
 */

public class PhoneViewHolder extends BaseViewHolder {
    private TextView mNickName;
    public PhoneViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Object o, int position) {
        if (o instanceof PhoneModel){
            PhoneModel phoneModel = (PhoneModel) o;
            mNickName.setText(phoneModel.nickname);
        }
    }

    @Override
    public void initView(View itemView) {
        mNickName = itemView.findViewById(R.id.nick_name);
    }
}
