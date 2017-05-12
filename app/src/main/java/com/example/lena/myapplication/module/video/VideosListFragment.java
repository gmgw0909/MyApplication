package com.example.lena.myapplication.module.video;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.example.lena.myapplication.R;
import com.example.lena.myapplication.api.bean.WelfarePhotoList;
import com.example.lena.myapplication.base.BaseFragment;
import com.example.lena.myapplication.utils.SnackBarUtils;
import com.example.lena.myapplication.widget.RecyclerViewDivider;
import com.example.lena.myapplication.widget.RecyclerViewWithFooter.OnLoadMoreListener;
import com.example.lena.myapplication.widget.RecyclerViewWithFooter.RecyclerViewWithFooter;

import butterknife.Bind;

/**
 * 共有的PhotosListFragment  通过传入不同的CategoryName  newInstance（创建不同实例）
 */
public class VideosListFragment extends BaseFragment implements VideoContract.IVideoView, SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {

    @Bind(R.id.recyclerView)
    RecyclerViewWithFooter mRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private String tabTitleName;
    private VideoRecyclerAdapter mAdapter;
    private VideoContract.IVideoPresenter iVideoPresenter;
    public static final String CATEGORY_NAME = "CATEGORY_NAME";

    public static VideosListFragment newInstance(String tabTitleName) {
        VideosListFragment photosListFragment = new VideosListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CATEGORY_NAME, tabTitleName);
        photosListFragment.setArguments(bundle);
        return photosListFragment;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photos_list;
    }

    @Override
    protected void initViews() {
        tabTitleName = getArguments().getString(CATEGORY_NAME);
        iVideoPresenter = new VideoPresenter(this, tabTitleName);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter = new VideoRecyclerAdapter(getActivity());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setOnLoadMoreListener(this);
        mRecyclerView.setEmpty();
        iVideoPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (iVideoPresenter != null) {
            iVideoPresenter.unSubscribe();
        }
    }

    @Override
    public void onRefresh() {
        iVideoPresenter.getDataList(true);
    }

    @Override
    public void onLoadMore() {
        iVideoPresenter.getDataList(false);
    }

    @Override
    public void getDataFail(String failMessage) {
        if (getUserVisibleHint()) {
            SnackBarUtils.ShowNormal(getActivity(), failMessage);
        }
    }

    @Override
    public void setDataList(WelfarePhotoList categoryResult) {
        mAdapter.setData(categoryResult.getResults());
    }

    @Override
    public void addDataList(WelfarePhotoList categoryResult) {
        mAdapter.addData(categoryResult.getResults());
    }

    @Override
    public void showSwipeLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideSwipeLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setCanLoading() {
        mRecyclerView.setLoading();
    }

    @Override
    public void setNoMore() {
        mRecyclerView.setEnd("没有更多数据");
    }
}
