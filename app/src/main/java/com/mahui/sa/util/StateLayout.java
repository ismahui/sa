package com.mahui.sa.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mahui.sa.R;

/**
 * Created by minghui on 2018/1/16.
 */

public class StateLayout extends FrameLayout {
    private final Object lock = new Object();
    private int errorLayoutID;
    private int emptyLayoutID;
    private int noNetworkLayoutID;
    private int loadingLayoutID;
    private View errorLayout;
    private View emptyLayout;
    private View loadingLayout;
    private View noNetWorkLayout;
    private LayoutInflater mLayoutInflater;
    private State mState;

    public StateLayout(@NonNull Context context) {
        super(context);
        this.init(context,null,0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.init(context,attrs,0);
    }

    public StateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init(context,attrs,defStyleAttr);
    }

    public void init(Context context ,AttributeSet attrs , int defStyleAttr){
        if (attrs != null){
            mLayoutInflater = LayoutInflater.from(context);
            TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.StateLayout,defStyleAttr,0);
            if (array !=null){
                errorLayoutID = array.getResourceId(R.styleable.StateLayout_error_layout,-1);
                emptyLayoutID = array.getResourceId(R.styleable.StateLayout_empty_layout,-1);
                noNetworkLayoutID = array.getResourceId(R.styleable.StateLayout_no_network_layout,-1);
                loadingLayoutID = array.getResourceId(R.styleable.StateLayout_loadind_layout,-1);



            }
            array.recycle();
        }
    }

    public void setState(State state){
        mState = state;
        changeState(mState);
    }

    public void changeState(State state){
        removeAllView();
        ViewGroup viewGroup;
        switch (state){
            case ERROR:
                if(this.errorLayoutID <= 0 && this.errorLayout == null) {
                    break;
                }

                if(this.errorLayout == null) {
                    try {
                        this.errorLayout = this.mLayoutInflater.inflate(this.errorLayoutID, this, false);
                    } catch (OutOfMemoryError var12) {
                        ;
                    }
                }

                if(this.errorLayout != null) {
                    try {
                        if (this.errorLayout.getParent() != null) {
                            viewGroup = (ViewGroup) this.errorLayout.getParent();
                            viewGroup.removeView(this.errorLayout);
                        }
                    } catch (Exception var11) {
                        ;
                    }

                    this.addView(this.errorLayout, new LayoutParams(-1, -1));
                    this.errorLayout.bringToFront();
                }
                break;
            case LOADING:
                if(this.loadingLayoutID <= 0 && this.loadingLayout == null) {
                    break;
                }

                if(this.loadingLayout == null) {
                    try {
                        this.loadingLayout = this.mLayoutInflater.inflate(this.loadingLayoutID, this, false);
                    } catch (OutOfMemoryError var12) {
                        ;
                    }
                }

                if(this.loadingLayout != null) {
                    try {
                        if (this.loadingLayout.getParent() != null) {
                            viewGroup = (ViewGroup) this.loadingLayout.getParent();
                            viewGroup.removeView(this.loadingLayout);
                        }
                    } catch (Exception var11) {
                        ;
                    }

                    this.addView(this.loadingLayout, new LayoutParams(-1, -1));
                    this.loadingLayout.bringToFront();
                }
                break;
            case EMPTY:
                if(this.emptyLayoutID <= 0 && this.emptyLayout == null) {
                    break;
                }

                if(this.emptyLayout == null) {
                    try {
                        this.emptyLayout = this.mLayoutInflater.inflate(this.emptyLayoutID, this, false);
                    } catch (OutOfMemoryError var12) {
                        ;
                    }
                }

                if(this.emptyLayout != null) {
                    try {
                        if (this.emptyLayout.getParent() != null) {
                            viewGroup = (ViewGroup) this.emptyLayout.getParent();
                            viewGroup.removeView(this.emptyLayout);
                        }
                    } catch (Exception var11) {
                        ;
                    }

                    this.addView(this.emptyLayout, new LayoutParams(-1, -1));
                    this.emptyLayout.bringToFront();
                }
                break;
            case NONETWORK:
                if(this.noNetworkLayoutID <= 0 && this.noNetWorkLayout == null) {
                    break;
                }

                if(this.noNetWorkLayout == null) {
                    try {
                        this.noNetWorkLayout = this.mLayoutInflater.inflate(this.noNetworkLayoutID, this, false);
                    } catch (OutOfMemoryError var12) {
                        ;
                    }
                }

                if(this.noNetWorkLayout != null) {
                    try {
                        if (this.noNetWorkLayout.getParent() != null) {
                            viewGroup = (ViewGroup) this.noNetWorkLayout.getParent();
                            viewGroup.removeView(this.noNetWorkLayout);
                        }
                    } catch (Exception var11) {
                        ;
                    }

                    this.addView(this.noNetWorkLayout, new LayoutParams(-1, -1));
                    this.noNetWorkLayout.bringToFront();
                }
                break;
            case ACCESS:
                break;

        }
    }

    private void removeAllView(){
        if (errorLayout != null) {
            this.removeView(errorLayout);
        }
        if (loadingLayout != null){
            this.removeView(loadingLayout);
        }
        if (emptyLayout != null){
            this.removeView(emptyLayout);
        }
        if (noNetWorkLayout != null){
            this.removeView(noNetWorkLayout);
        }

    }


    public  enum State{
        LOADING,
        ERROR,
        EMPTY,
        NONETWORK,
        ACCESS
    }
}
