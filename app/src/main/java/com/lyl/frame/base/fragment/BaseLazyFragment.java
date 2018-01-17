package com.lyl.frame.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author lyl
 * @date 2018/1/16.
 * BaseLazyFragment
 * 实现懒加载模式
 */

public abstract class BaseLazyFragment extends Fragment {

    public boolean isUIVisible = false;
    private boolean isCreated = false;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        isCreated=true;
        lazyLoad();
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        if (isCreated && isUIVisible) {
            queryData();
            isUIVisible = false;
            isCreated = false;
        }
    }

    /**
     * 查询数据
     */
    public abstract void queryData();


    /**
     * 获取界面布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化界面
     */
    public abstract void initView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        //页面销毁,恢复标记
        isCreated = false;
        isUIVisible = false;
    }
}
