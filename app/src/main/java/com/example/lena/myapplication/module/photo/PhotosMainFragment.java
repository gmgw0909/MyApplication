package com.example.lena.myapplication.module.photo;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.example.lena.myapplication.R;
import com.example.lena.myapplication.base.BaseFragment;
import com.example.lena.myapplication.base.ViewPagerAdapter;
import com.example.lena.myapplication.utils.Constants;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 图片主界面
 */
public class PhotosMainFragment extends BaseFragment {

    @Bind(R.id.tool_bar)
    Toolbar mToolBar;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    ViewPagerAdapter mPagerAdapter;
    String[] tabTitles = {"Android", "福利", "拓展资源"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photos_main;
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, Constants.PHOTOS);
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (String tabTitle : tabTitles) {
            fragments.add(PhotosListFragment.newInstance(tabTitle));
        }
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mPagerAdapter.setItems(fragments, tabTitles);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);//设置TabLayout与ViewPager联动
//        mViewPager.setCurrentItem(0);//加载第一个page页
    }
}
