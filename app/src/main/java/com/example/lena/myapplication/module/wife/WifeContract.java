package com.example.lena.myapplication.module.wife;


import com.example.lena.myapplication.base.BasePresenter;
import com.example.lena.myapplication.base.BaseView;

import java.util.List;

/**
 * VideoContract(IPhotoView  IPhotoPresenter)
 */

public interface WifeContract {

    interface IWifeView extends BaseView {


    }

    interface IWifePresenter extends BasePresenter {

        List<Integer> getDataList();

    }

}
