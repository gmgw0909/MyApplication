package com.example.lena.myapplication.module.photo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lena.myapplication.R;
import com.example.lena.myapplication.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/5/11
 */

public class PhotoDetailsActivity extends BaseActivity implements PhotoContract.IPhotoWebView {

    @Bind(R.id.web_view)
    WebView webView;
    @Bind(R.id.web_toolbar)
    Toolbar mWebToolbar;
    @Bind(R.id.web_title)
    TextView mWebTitle;
    @Bind(R.id.web_progressBar)
    ProgressBar mWebProgressBar;

    private PhotoWebPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos_details);
        ButterKnife.bind(this);
        presenter = new PhotoWebPresenter(this);
        presenter.subscribe();
        mWebToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mWebProgressBar.setVisibility(View.VISIBLE);
                mWebProgressBar.setProgress(newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                mWebProgressBar.setVisibility(View.GONE);
            }
        });
        WebSettings webSettings = webView.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
    }

    @Override
    public void setUrl(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void setTitle(String title) {
        mWebTitle.setText(title);
    }

    @Override
    public Activity getActivityContext() {
        return this;
    }
}
