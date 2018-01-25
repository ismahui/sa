package com.mahui.sa.business;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mahui.sa.R;
import com.mahui.sa.business.phone.view.PhoneActivity;
import com.mahui.sa.business.photo.view.PhotoActivity;
import com.mahui.sa.business.sms.view.SmsManageActivity;
import com.mahui.sa.util.Nav;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mPhoneManage;
    private Button mMessageManage;
    private Button mPhotoManage;
    private Button mBackUpManage;
    private Button mSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        mPhoneManage.setOnClickListener(this);
        mMessageManage.setOnClickListener(this);
        mBackUpManage.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mPhotoManage.setOnClickListener(this);

    }

    private void initView() {
        mPhoneManage = findViewById(R.id.phone_manage);
        mMessageManage = findViewById(R.id.message_manage);
        mBackUpManage = findViewById(R.id.backup_manage);
        mSetting = findViewById(R.id.setting);
        mPhotoManage = findViewById(R.id.photo_manage);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.phone_manage:
                Nav.nav(this, PhoneActivity.class,null);
                break;
            case R.id.message_manage:
                Nav.nav(this, SmsManageActivity.class,null);
                break;
            case R.id.backup_manage:
                break;
            case R.id.photo_manage:
                Nav.nav(this, PhotoActivity.class,null);
                break;
            case R.id.setting:
                break;


        }
    }
}
