package com.example.song.mytest;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by song on 2018/11/16.
 */

public class AutoScrollRecyclerView extends RecyclerView {
    private RecyclerView mRecyclerView;
    private TwoActivity mContext;
    private Disposable mAutoTask = null;
    private LinearSmoothScroller mSmoothScroll;
    public int runTime=1;
    public int intervalTime=5;

    public AutoScrollRecyclerView(Context context) {
        this(context, null, 0);
    }

    public AutoScrollRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoScrollRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setSmoothScroll(TwoActivity context, RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        this.mContext = context;
        mSmoothScroll = new NewLinearSmoothScroller(mContext);
        //禁止滑动
        setNestedScrollingEnabled(false);
    }

    //全屏自动滚动 开始
    public void start() {
        if (mAutoTask != null && !mAutoTask.isDisposed()) {
            mAutoTask.dispose();
        }
        mAutoTask = Observable.interval(1000, 100, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        smoothScrollBy(0, 20);
                    }
                });
    }

    //全屏自动滚动 停止
    public void stop() {
        if (mAutoTask != null && !mAutoTask.isDisposed()) {
            mAutoTask.dispose();
            mAutoTask = null;
        }
    }
    long pauseTime=0;
    boolean isRun;
    //跑马灯滚动 开始
    public void startAuto() {
        if (mAutoTask != null && !mAutoTask.isDisposed()) {
            mAutoTask.dispose();
        }
        isRun=true;
        mAutoTask = Observable.interval(runTime, intervalTime, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                     int firstVisibleItemPosition = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
                        int currentIndex = firstVisibleItemPosition;
                        if (++currentIndex == mContext.mAdapter.getItemCount()) {
                            mSmoothScroll.setTargetPosition(0);
                        } else {
                            mSmoothScroll.setTargetPosition(currentIndex);
                        }
                        getLayoutManager().startSmoothScroll(mSmoothScroll);
                    }
                });
    }

    //跑马灯滚动 停止
    public void stopAuto() {
        if (mAutoTask != null && !mAutoTask.isDisposed()) {
            mAutoTask.dispose();
            mAutoTask = null;
        }
    }

    //重写滚动方法
    public class NewLinearSmoothScroller extends LinearSmoothScroller {

        public NewLinearSmoothScroller(Context context) {
            super(context);
        }

        @Override
        protected int getVerticalSnapPreference() {
            return LinearSmoothScroller.SNAP_TO_START;
        }

        @Override
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            // 移动一英寸需要花费3ms
            return 3f / (displayMetrics.density = 1f);
        }
    }
}
