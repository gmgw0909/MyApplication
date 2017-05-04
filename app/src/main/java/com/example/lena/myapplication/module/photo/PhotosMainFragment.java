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

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_photos_main;
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, Constants.PHOTOS);
        ArrayList<Fragment> fragments = new ArrayList<>();
        PhotosListFragment fragment1 = PhotosListFragment.newInstance("美女");
        PhotosListFragment fragment2 = PhotosListFragment.newInstance("福利");
        PhotosListFragment fragment3 = PhotosListFragment.newInstance("生活");
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mPagerAdapter.setItems(fragments, new String[]{"美女", "福利", "生活"});
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);//设置TabLayout与ViewPager联动
//        mViewPager.setCurrentItem(0);//加载第一个page页
    }
}
