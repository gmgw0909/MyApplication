package com.example.lena.myapplication.module.photo;


import android.app.Activity;

import com.example.lena.myapplication.api.bean.WelfarePhotoList;
import com.example.lena.myapplication.base.BasePresenter;
import com.example.lena.myapplication.base.BaseView;

/**
 * VideoContract(IPhotoView  IPhotoPresenter)
 */

public interface PhotoContract {

    interface IPhotoView extends BaseView {

        void getDataFail(String failMessage);

        void setDataList(WelfarePhotoList categoryResult);

        void addDataList(WelfarePhotoList categoryResult);

        void showSwipeLoading();

        void hideSwipeLoading();

        void setCanLoading();//可以继续加载

        void setNoMore(); //不可以继续加载
    }

    interface IPhotoPresenter extends BasePresenter {

        void getDataList(boolean isRefresh);

        String getTabTitleName();
    }

    interface IPhotoWebView extends BaseView {
        void setUrl(String url);

        void setTitle(String title);

        Activity getActivityContext();
    }

    interface IPhotoWebPresenter extends BasePresenter {
        String getTitle();

        String getUrl();
    }
}
