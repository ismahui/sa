package com.mahui.sa.business.photo.view;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.mahui.sa.R;
import com.mahui.sa.util.BaseActivity;
import com.viewpagerindicator.TabPageIndicator;

/**
 * Created by minghui on 2018/1/17.
 */

public class PhotoActivity extends BaseActivity{

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
        return "图片管理";
    }

    private static class TabPageIndicatorAdapter extends FragmentPagerAdapter{
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new LocalPhotoFragment();
            } else  {
                return new RemotePhotoFragment();
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

    @Override
    public int initActionBarColor() {
        return Color.BLUE;
    }
}
