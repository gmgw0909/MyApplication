package com.example.lena.myapplication.module.video;

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
 * 视频主界面
 */
public class VideosMainFragment extends BaseFragment {

    @Bind(R.id.tool_bar)
    Toolbar mToolBar;
    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    ViewPagerAdapter mPagerAdapter;
    String[] tabTitles = {"all", "android", "java", "html", "javascript", "ios", "vuejs", "unity3d"};

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_videos_main;
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, Constants.VIDEOS);
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (String tabTitle : tabTitles) {
            fragments.add(VideosListFragment.newInstance(tabTitle));
        }
        mPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        mPagerAdapter.setItems(fragments, tabTitles);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);//设置TabLayout与ViewPager联动
    }
}
