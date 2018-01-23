package com.mahui.sa.business.sms.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mahui.sa.R;
import com.mahui.sa.business.photo.view.LocalPhotoFragment;
import com.mahui.sa.business.photo.view.PhotoActivity;
import com.mahui.sa.business.photo.view.RemotePhotoFragment;
import com.mahui.sa.business.sms.model.MessageModel;
import com.mahui.sa.business.sms.presenter.SmsPresenter;
import com.mahui.sa.util.BaseActivity;
import com.mahui.sa.util.SmsUtil;
import com.mahui.sa.util.StateLayout;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by minghui on 2018/1/16.
 */

public class SmsManageActivity extends BaseActivity implements ISmsView{
    private TabPageIndicator mTabPageIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private static final String[] TITLE ={"本地","远程"};

    @Override
    protected void initListener() {
        mTabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        mTabPageIndicator = findViewById(R.id.indicator);
        mViewPager = findViewById(R.id.viewpager);
        mFragmentPagerAdapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabPageIndicator.setViewPager(mViewPager);

    }

    @Override
    public View onContentViewInit(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.activity_photo,null,false);
    }

    @Override
    public String initActionBarTitle() {
        return "短信管理";
    }

    @Override
    public void updateList(List<MessageModel> list) {

    }

    @Override
    public Context getContext() {
        return null;
    }

    private static class TabPageIndicatorAdapter extends FragmentPagerAdapter{
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new LocalSmsFragment();
            } else  {
                return new RemoteSmsFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position% TITLE.length];
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }

    }
}
