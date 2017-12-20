package com.example.lena.myapplication.module.wife;

import android.content.Context;
import android.widget.ImageView;

import com.example.lena.myapplication.R;
import com.example.lena.myapplication.base.CommonRecyclerAdapter;
import com.example.lena.myapplication.base.CommonRecyclerHolder;

import java.util.List;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/12/20
 */

public class WifeGalleryAdapter extends CommonRecyclerAdapter<Integer> {
    public WifeGalleryAdapter(Context context, List<Integer> images, int layoutId) {
        super(context, images, layoutId);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, Integer integer) {
        ImageView imageView = holder.getView(R.id.iv);
        imageView.setImageResource(integer);
    }
}
