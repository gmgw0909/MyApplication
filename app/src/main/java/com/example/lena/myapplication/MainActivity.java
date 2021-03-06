package com.example.lena.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.example.lena.myapplication.base.BaseActivity;
import com.example.lena.myapplication.module.photo.PhotosMainFragment;
import com.example.lena.myapplication.module.wife.WifeFragment;
import com.example.lena.myapplication.utils.Constants;
import com.example.lena.myapplication.utils.SnackBarUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    PhotosMainFragment fragment1;
    WifeFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragment1 = new PhotosMainFragment();
        fragment2 = new WifeFragment();
        //设置NavigationView 的选择监听
        mNavView.setNavigationItemSelectedListener(this);
        //NavigationView MenuItem设置默认选中
        mNavView.setCheckedItem(R.id.nav_photos);
        //加入第一个fragment
        addFragment(R.id.fl_container, fragment1, Constants.PHOTOS);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        item.setChecked(true);
        if (item.getItemId() == R.id.nav_photos) {
            replaceFragment(R.id.fl_container, fragment1, Constants.PHOTOS);
        } else if (item.getItemId() == R.id.nav_videos) {
            replaceFragment(R.id.fl_container, fragment2, Constants.WIFE);
        } else if (item.getItemId() == R.id.nav_setting) {
            item.setChecked(false);
            SnackBarUtils.ShowNormal(this, "设置");
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // 左侧栏是否打开
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            exitApp();
        }
    }

    /**
     * 退出
     */
    private long mExitTime = 0;

    private void exitApp() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            SnackBarUtils.ShowNormal(this, "再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

}
