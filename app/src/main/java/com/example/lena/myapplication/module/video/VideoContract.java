package com.example.lena.myapplication.module.video;


import com.example.lena.myapplication.api.bean.WelfarePhotoList;
import com.example.lena.myapplication.base.BasePresenter;
import com.example.lena.myapplication.base.BaseView;

/**
 * VideoContract(IPhotoView  IPhotoPresenter)
 */

public interface VideoContract {

    interface IVideoView extends BaseView {

        void getDataFail(String failMessage);

        void setDataList(WelfarePhotoList categoryResult);

        void addDataList(WelfarePhotoList categoryResult);

        void showSwipeLoading();

        void hideSwipeLoading();

        void setCanLoading();//可以继续加载

        void setNoMore(); //不可以继续加载
    }

    interface IVideoPresenter extends BasePresenter {

        void getDataList(boolean isRefresh);

        String getTabTitleName();
    }

}
