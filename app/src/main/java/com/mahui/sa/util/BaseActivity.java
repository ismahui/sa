package com.mahui.sa.util;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mahui.sa.R;

/**
 * Created by minghui on 2018/1/17.
 */

public abstract class BaseActivity extends Activity implements View.OnClickListener{
    private LinearLayout mContentLayout;
    private LinearLayout mLeftAcyionBarlayout;
    private LinearLayout mRightAcyionBarlayout;
    private View mActionBarLine;
    private TextView mTitle;
    private TextView mBack;
    private LayoutInflater mLayoutInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        onActionBarViewCreated();
        onActionBarViewClick();
        initView();
        initListener();
    }

    protected abstract void initListener();

    public void onActionBarViewCreated(){
        mBack = findViewById(R.id.back);
        mActionBarLine = findViewById(R.id.action_bar_line);
        mLeftAcyionBarlayout = findViewById(R.id.left_panel);
        mRightAcyionBarlayout = findViewById(R.id.right_panel);
        mTitle = findViewById(R.id.title);
        mTitle.setText(initActionBarTitle());
    }

    public void onActionBarViewClick(){
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.back:
                super.onBackPressed();
            break;
        }
    }

    public void initView(){
        mContentLayout =  findViewById(R.id.content_panel);
        mLayoutInflater =LayoutInflater.from(this);
        mContentLayout.addView(onContentViewInit(mLayoutInflater));
    }

    public abstract View onContentViewInit(LayoutInflater layoutInflater);

    public abstract String initActionBarTitle();
}
