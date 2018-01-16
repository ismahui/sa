package com.mahui.sa.util;


import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by minghui on 2018/1/16.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }



    public abstract void bindData(Object o ,int position);
}
