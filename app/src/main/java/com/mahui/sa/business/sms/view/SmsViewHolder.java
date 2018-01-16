package com.mahui.sa.business.sms.view;

import android.view.View;
import android.widget.TextView;

import com.mahui.sa.R;
import com.mahui.sa.business.sms.model.MessageModel;
import com.mahui.sa.util.BaseViewHolder;

/**
 * Created by minghui on 2018/1/16.
 */

public class SmsViewHolder extends BaseViewHolder {
    private TextView mNickName;
    private TextView mTime;
    private TextView mContent;

    public SmsViewHolder(View itemView) {
        super(itemView);
        mNickName = itemView.findViewById(R.id.nick_name);
        mTime = itemView.findViewById(R.id.time);
        mContent = itemView.findViewById(R.id.content);
    }


    @Override
    public void bindData(Object o, int position) {
        if (o instanceof MessageModel){
            MessageModel messageModel = (MessageModel) o;
            mNickName.setText(messageModel.address);
            mTime.setText(messageModel.date+"");
            mContent.setText(messageModel.body);
        }
    }
}
