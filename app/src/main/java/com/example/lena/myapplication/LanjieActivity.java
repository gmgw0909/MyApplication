package com.example.lena.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lena.myapplication.api.AppNetRequest;
import com.example.lena.myapplication.api.bean.WelfarePhotoList;
import com.example.lena.myapplication.base.BaseActivity;
import com.example.lena.myapplication.base.CommonRecyclerAdapter;
import com.example.lena.myapplication.base.CommonRecyclerHolder;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/5/12
 */

public class LanjieActivity extends BaseActivity {
    DiscreteScrollView scrollView;
    List<String> list = new ArrayList<>();
    MyAdapter adapter;
    private int page = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lan);
        scrollView = (DiscreteScrollView) findViewById(R.id.picker);
        findViewById(R.id.web_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollToPosition(0);
                page++;
                refreshData();
            }
        });
        adapter = new MyAdapter(LanjieActivity.this, list, R.layout.item_image);
        scrollView.setAdapter(adapter);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.0f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.CENTER)
                .build());

        refreshData();
    }

    private void refreshData() {
        AppNetRequest.getWelfareApi()
                .getPhotoByTitle("福利", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WelfarePhotoList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(WelfarePhotoList categoryResult) {
                        int size = categoryResult.getResults().size();
                        list.clear();
                        for (int i = 0; i < size; i++) {
                            list.add(categoryResult.getResults().get(i).getUrl());
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    class MyAdapter extends CommonRecyclerAdapter<String> {
        public MyAdapter(Context context, List<String> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void convert(CommonRecyclerHolder holder, String url) {
            ImageView imageView = holder.getView(R.id.iv);
            if (!TextUtils.isEmpty(url)) {
                Glide.with(mContext)
                        .load(url)
                        .placeholder(R.mipmap.image_default)
                        .error(R.mipmap.image_default)
                        .into(imageView);
            } else { // 列表没有images就显示url
                Glide.with(mContext).load(R.mipmap.image_default).into(imageView);
            }
        }
    }
}
