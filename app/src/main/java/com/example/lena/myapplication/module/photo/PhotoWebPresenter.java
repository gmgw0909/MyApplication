package com.example.lena.myapplication.module.photo;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/5/11
 */

public class PhotoWebPresenter implements PhotoContract.IPhotoWebPresenter {

    private PhotoContract.IPhotoWebView iPhotoWebView;
    private String title;
    private String url;

    public PhotoWebPresenter(PhotoContract.IPhotoWebView iPhotoWebView) {
        this.iPhotoWebView = iPhotoWebView;
    }

    @Override
    public void subscribe() {
        title = iPhotoWebView.getActivityContext().getIntent().getStringExtra("title");
        url = iPhotoWebView.getActivityContext().getIntent().getStringExtra("url");
        iPhotoWebView.setUrl(url);
        iPhotoWebView.setTitle(title);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
