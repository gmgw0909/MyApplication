package com.example.lena.myapplication.module.video;


import com.example.lena.myapplication.api.AppNetRequest;
import com.example.lena.myapplication.api.bean.WelfarePhotoList;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * IPhotoPresenter
 */

public class VideoPresenter implements VideoContract.IVideoPresenter {

    private VideoContract.IVideoView iVideoView;
    private int mPage = 1;
    private Subscription mSubscription;
    private String tabTitleName;

    public VideoPresenter(VideoContract.IVideoView iVideoView, String tabTitleName) {
        this.iVideoView = iVideoView;
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
            iVideoView.showSwipeLoading();
        } else {
            mPage++;
        }
        mSubscription = AppNetRequest.getImoocApi()
                .getVideoByTitle("1494566072145","5306221","1cbd32264affe227507b9a32115974f7","20336ffe8c94f0b61a12af45c56fe351","0","223","1","83933a88b40831a111a74c550ec01432","0")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WelfarePhotoList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        iVideoView.hideSwipeLoading();
                        iVideoView.getDataFail(tabTitleName + " 列表数据获取失败！");
                    }

                    @Override
                    public void onNext(WelfarePhotoList categoryResult) {
                        if (isRefresh) {
                            iVideoView.setDataList(categoryResult);
                            iVideoView.hideSwipeLoading();
                        } else {
                            iVideoView.addDataList(categoryResult);
                        }
                        if (categoryResult.getResults() != null && categoryResult.getResults().size() >= 10) {
                            iVideoView.setCanLoading();//告诉RecycleView还是加载更多数据
                        } else {
                            iVideoView.setNoMore();//告诉RecycleView加载完就没有数据了
                        }
                    }
                });

    }

    @Override
    public String getTabTitleName() {
        return tabTitleName;
    }
}
