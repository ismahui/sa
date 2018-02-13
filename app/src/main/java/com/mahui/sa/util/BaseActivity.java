package com.mahui.sa.util;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mahui.sa.R;

/**
 * Created by minghui on 2018/1/17.
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    private LinearLayout mContentLayout;
    private LinearLayout mLeftAcyionBarlayout;
    private LinearLayout mRightAcyionBarlayout;
    private View mActionBarLine;
    private TextView mTitle;
    private TextView mBack;
    private LayoutInflater mLayoutInflater;
    private RelativeLayout mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        if (isNeedImmersion()) {
            ViewGroup rootView = this.getWindow().getDecorView().findViewById(android.R.id.content);
            rootView.setPadding(0, getStatusBarHeight(), 0, 0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                    Window window = this.getWindow();
                    View decorView = window.getDecorView();
                    //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                    int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    decorView.setSystemUiVisibility(option);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(initActionBarColor());
                    //导航栏颜色也可以正常设置
                    window.setNavigationBarColor(initActionBarColor());
                } else {
                    Window window = this.getWindow();
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                    int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                    attributes.flags |= flagTranslucentStatus;
                    attributes.flags |= flagTranslucentNavigation;
                    window.setAttributes(attributes);
                }
            }
        }
        if (initActionBar()){
            onActionBarViewCreated();
            onActionBarViewClick();
        }
        initView();
        initListener();
    }

    protected abstract void initListener();

    public void onActionBarViewCreated() {
        mActionBar = findViewById(R.id.actionbar);
        mActionBar.setBackgroundColor(initActionBarColor());
        mBack = findViewById(R.id.back);
        mActionBarLine = findViewById(R.id.action_bar_line);
        mLeftAcyionBarlayout = findViewById(R.id.left_panel);
        mRightAcyionBarlayout = findViewById(R.id.right_panel);
        mTitle = findViewById(R.id.title);
        mTitle.setText(initActionBarTitle());
    }

    public void onActionBarViewClick() {
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back:
                super.onBackPressed();
                break;
        }
    }

    public void initView() {
        mContentLayout = findViewById(R.id.content_panel);
        mLayoutInflater = LayoutInflater.from(this);
        mContentLayout.addView(onContentViewInit(mLayoutInflater));
    }

    public abstract View onContentViewInit(LayoutInflater layoutInflater);

    public abstract String initActionBarTitle();

    public boolean isNeedImmersion() {
        return true;
    }

    public int initActionBarColor(){
        return getResources().getColor(R.color.skyblue);
    }

    /**
     * 利用反射获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        int result = 0;
        //获取状态栏高度的资源id
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public boolean initActionBar(){
        return true;
    }
}
