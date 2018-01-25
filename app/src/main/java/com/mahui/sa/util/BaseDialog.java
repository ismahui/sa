package com.mahui.sa.util;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by mahui on 2018/1/25.
 */

public abstract class BaseDialog extends Dialog {
    private View mContentView;
    public BaseDialog(@NonNull Context context) {
        super(context);
        doAction();
    }


    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        doAction();
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        doAction();
    }
    public abstract View onContetViewInit(LayoutInflater layoutInflater);
    public abstract void initView(View view);
    public abstract void initData();
    public abstract void initListener();

    public void setAttributes(WindowManager manager) {
        Window window = this.getWindow();
        Display d = manager.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = getWindow().getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (d.getHeight()*0.8); // 高度设置为屏幕的0.6
        p.width = d.getWidth(); // 宽度设置为屏幕的0.95
        window.setAttributes(p);
        window.setGravity(Gravity.CENTER);
    }

    private void doAction(){
        mContentView = onContetViewInit(getLayoutInflater());
        this.setContentView(mContentView);
        this.initView(mContentView);
        this.initListener();
        this.setCancelable(false);
    }
}
