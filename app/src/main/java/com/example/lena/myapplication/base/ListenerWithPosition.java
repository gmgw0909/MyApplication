package com.example.lena.myapplication.base;

import android.view.View;

/**
 * 通用的RecyclerView的item 点击Listener
 */

public class ListenerWithPosition implements View.OnClickListener {

    private int mPosition;
    private Object mHolder;
    private OnClickWithPositionListener mOnClickListener;

    public ListenerWithPosition(int position, Object holder) {
        this.mPosition = position;
        this.mHolder = holder;
    }

    @Override
    public void onClick(View v) {
        if (mOnClickListener != null)
            mOnClickListener.onClick(v, mPosition, mHolder);
    }

    public interface OnClickWithPositionListener<T> {
        void onClick(View v, int position, T holder);
    }

    public void setOnClickListener(OnClickWithPositionListener mOnClickListener) {
        this.mOnClickListener = mOnClickListener;
    }
}
