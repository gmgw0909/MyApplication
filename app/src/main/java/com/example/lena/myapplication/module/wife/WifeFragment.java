package com.example.lena.myapplication.module.wife;

import android.support.v7.widget.Toolbar;

import com.example.lena.myapplication.R;
import com.example.lena.myapplication.base.BaseFragment;
import com.example.lena.myapplication.utils.Constants;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 创建者 LeeBoo
 * 创建时间 2017/5/12
 */

public class WifeFragment extends BaseFragment implements WifeContract.IWifeView {

    @Bind(R.id.tool_bar)
    Toolbar mToolBar;
    @Bind(R.id.picker)
    DiscreteScrollView scrollView;

    List<Integer> list = new ArrayList<>();
    WifeGalleryAdapter adapter;
    WifeContract.IWifePresenter iWifePresenter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_wife;
    }

    @Override
    protected void initViews() {
        initToolBar(mToolBar, true, Constants.WIFE);
        iWifePresenter = new WifePresenter();
        list = iWifePresenter.getDataList();
        adapter = new WifeGalleryAdapter(getActivity(), list, R.layout.item_image);
        scrollView.setAdapter(adapter);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.0f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.CENTER)
                .build());
    }
}
