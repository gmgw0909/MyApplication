package com.example.lena.myapplication.module.photo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lena.myapplication.R;
import com.example.lena.myapplication.api.bean.WelfarePhotoInfo;
import com.example.lena.myapplication.base.CommonRecyclerAdapter;
import com.example.lena.myapplication.base.CommonRecyclerHolder;
import com.example.lena.myapplication.base.ListenerWithPosition;
import com.example.lena.myapplication.utils.TimeUtil;

/**
 * PhotoRecyclerAdapter
 */

class PhotoRecyclerAdapter extends CommonRecyclerAdapter<WelfarePhotoInfo> implements ListenerWithPosition.OnClickWithPositionListener<CommonRecyclerHolder> {

    PhotoRecyclerAdapter(Context context) {
        super(context, null, R.layout.item_category);
    }

    @Override
    public void convert(CommonRecyclerHolder holder, WelfarePhotoInfo resultsBean) {
        if (resultsBean != null) {
            ImageView imageView = holder.getView(R.id.category_item_img);
            if (resultsBean.getImages() != null && !TextUtils.isEmpty(resultsBean.getImages().get(0))) {
                Glide.with(mContext)
                        .load(resultsBean.getImages().get(0) + "?imageView2/0/w/100")//节省流量
                        .placeholder(R.mipmap.image_default)
                        .error(R.mipmap.image_default)
                        .into(imageView);
            } else { // 列表不显示图片
                Glide.with(mContext).load(R.mipmap.image_default).into(imageView);
            }
            holder.setTextViewText(R.id.category_item_desc, resultsBean.getDesc() == null ? "unknown" : resultsBean.getDesc());
            holder.setTextViewText(R.id.category_item_author, resultsBean.getWho() == null ? "unknown" : resultsBean.getWho());
            holder.setTextViewText(R.id.category_item_time, TimeUtil.dateFormat(resultsBean.getPublishedAt()));
            holder.setOnClickListener(this, R.id.category_item_layout);
        }
    }

    @Override
    public void onClick(View v, int position, CommonRecyclerHolder holder) {
        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mData.get(position).getUrl())));
    }
}
