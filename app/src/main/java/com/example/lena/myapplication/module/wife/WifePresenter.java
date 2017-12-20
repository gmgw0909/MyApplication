package com.example.lena.myapplication.module.wife;

import com.example.lena.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/12/20
 */

public class WifePresenter implements WifeContract.IWifePresenter {

    List<Integer> list = new ArrayList<>();

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public List<Integer> getDataList() {
        list.add(R.mipmap.wx_1);
        list.add(R.mipmap.wx_2);
        list.add(R.mipmap.wx_3);
        list.add(R.mipmap.wx_4);
        list.add(R.mipmap.wx_5);
        list.add(R.mipmap.wx_6);
        list.add(R.mipmap.wx_7);
        list.add(R.mipmap.wx_8);
        list.add(R.mipmap.wx_9);
        list.add(R.mipmap.wx_10);
        list.add(R.mipmap.wx_11);
        list.add(R.mipmap.wx_12);
        list.add(R.mipmap.wx_13);
        list.add(R.mipmap.wx_14);
        list.add(R.mipmap.wx_15);
        list.add(R.mipmap.wx_16);
        list.add(R.mipmap.wx_17);
        list.add(R.mipmap.wx_18);
        list.add(R.mipmap.wx_19);
        list.add(R.mipmap.wx_20);
        list.add(R.mipmap.wx_21);
        list.add(R.mipmap.wx_22);
        list.add(R.mipmap.wx_23);
        list.add(R.mipmap.wx_24);
        list.add(R.mipmap.wx_25);
        list.add(R.mipmap.wx_26);
        list.add(R.mipmap.wx_27);
        list.add(R.mipmap.wx_28);
        list.add(R.mipmap.wx_29);
        list.add(R.mipmap.wx_30);
        list.add(R.mipmap.wx_31);
        list.add(R.mipmap.wx_32);
        list.add(R.mipmap.wx_33);
        list.add(R.mipmap.wx_34);
        list.add(R.mipmap.wx_35);
        return list;
    }
}
