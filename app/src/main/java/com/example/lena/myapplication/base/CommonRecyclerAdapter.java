package com.example.lena.myapplication.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的RecyclerViewAdapter基类
 */

public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter {
    protected Context mContext;
    protected List<T> mData;
    private int layoutId;
    private View mView;

    public void addData(List<T> data){
        if (data != null){
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void setData(List<T> data){
        mData.clear();
        addData(data);
    }

    public CommonRecyclerAdapter(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mData = data == null ? (List<T>) new ArrayList<>() : data;
        this.layoutId = layoutId;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(layoutId, parent, false);
        return new CommonRecyclerHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommonRecyclerHolder){
            CommonRecyclerHolder commonHolder = (CommonRecyclerHolder) holder;
            commonHolder.position = position;
            convert(commonHolder, mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public abstract void convert(CommonRecyclerHolder holder, T t);
}
