package com.example.lena.myapplication.module.photo;


import com.example.lena.myapplication.api.AppNetRequest;
import com.example.lena.myapplication.api.bean.WelfarePhotoList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * IPhotoPresenter
 */

public class PhotoPresenter implements PhotoContract.IPhotoPresenter {

    private PhotoContract.IPhotoView iPhotoView;
    private int mPage = 1;
    private Subscription mSubscription;
    private String tabTitleName;

    public PhotoPresenter(PhotoContract.IPhotoView iPhotoView, String tabTitleName) {
        this.iPhotoView = iPhotoView;
        this.tabTitleName = tabTitleName;
    }

    @Override
    public void subscribe() {
        getDataList(true);
    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void getDataList(final boolean isRefresh) {
        if (isRefresh) {
            mPage = 1;
            iPhotoView.showSwipeLoading();
        } else {
            mPage++;
        }
        mSubscription = AppNetRequest.getWelfareApi()
                .getPhotoByTitle(tabTitleName, mPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WelfarePhotoList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iPhotoView.hideSwipeLoading();
                        iPhotoView.getDataFail(tabTitleName + " 列表数据获取失败！");
                    }

                    @Override
                    public void onNext(WelfarePhotoList categoryResult) {
                        if (isRefresh) {
                            iPhotoView.setDataList(categoryResult);
                            iPhotoView.hideSwipeLoading();
                        } else {
                            iPhotoView.addDataList(categoryResult);
                        }
                        if (categoryResult.getResults() != null && categoryResult.getResults().size() >= 10) {
                            iPhotoView.setLoading();//告诉RecycleView还是加载更多数据
                        } else {
                            iPhotoView.setNoMore();//告诉RecycleView加载完就没有数据了
                        }
                    }
                });

    }

    @Override
    public String getTabTitleName() {
        return tabTitleName;
    }
}
