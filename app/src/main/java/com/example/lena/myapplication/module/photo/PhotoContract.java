package com.example.lena.myapplication.module.photo;


import com.example.lena.myapplication.api.bean.WelfarePhotoList;
import com.example.lena.myapplication.base.BasePresenter;
import com.example.lena.myapplication.base.BaseView;

/**
 * PhotoContract(IPhotoView  IPhotoPresenter)
 */

public interface PhotoContract {

    interface IPhotoView extends BaseView {

        void getDataFail(String failMessage);

        void setDataList(WelfarePhotoList categoryResult);

        void addDataList(WelfarePhotoList categoryResult);

        void showSwipeLoading();

        void hideSwipeLoading();

        void setLoading();

        void setNoMore();
    }

    interface IPhotoPresenter extends BasePresenter {

        void getDataList(boolean isRefresh);

        String getTabTitleName();
    }
}
