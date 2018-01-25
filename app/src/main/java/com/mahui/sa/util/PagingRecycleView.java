package com.mahui.sa.util;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


/**
 * Created by minghui on 2018/1/25.
 */

public class PagingRecycleView extends RecyclerView {
    private int mCurrentPage;
    private IPageLoad mIPageLoad;
    /**
     * 滚动方向
     */
    public PagingRecycleView(Context context) {
        super(context);
    }

    public PagingRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        switch (state) {
            case SCROLL_STATE_IDLE:
                LayoutManager layoutManager = getLayoutManager();
                int itemCount = getAdapter().getItemCount();
                int lastVisibleItemPosition = 0;
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                }
                if (lastVisibleItemPosition >= itemCount - 1) {
                    mIPageLoad.loadNextPage(mCurrentPage++);
                }
                break;
        }
    }


    public void setIPageLoad(IPageLoad IPageLoad) {
        mIPageLoad = IPageLoad;
    }

    public interface IPageLoad {
        void loadNextPage(int page);
    }
}
