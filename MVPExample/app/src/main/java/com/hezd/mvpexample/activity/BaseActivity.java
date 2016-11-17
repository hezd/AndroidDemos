package com.hezd.mvpexample.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.hezd.mvpexample.R;
import com.hezd.mvpexample.activity.IBaseView;
import com.hezd.mvpexample.presenter.BasePresenter;

/**
 *
 * BaseActivity抽象类，定义Activity通用属性行为。
 * 此基类主要功能：
 * 1.初始化根布局
 * 2.定义子Activity初始化界面方法setContentLayout
 * 3.定义子类通用行为，getViews，bindData，setListeners，以及默认调用关系。
 * 4.加载布局初始化，显示，隐藏。
 * Created by hezd on 2016/11/7.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    private FrameLayout mParentLayout;
    private ProgressBar mLoadingPb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);

        initViews();

        if(null!=getPresenter()) {
            getPresenter().onCreate();
        }
    }

    @Override
    public void initViews() {
        mParentLayout = (FrameLayout) findViewById(R.id.fl_content);
        mLoadingPb = (ProgressBar) findViewById(R.id.pb_loading);
    }

    @Override
    public void setContentLayout(int resId) {
        View.inflate(this,resId,mParentLayout);
        getViews();
        bindData();
        setListeners();
    }

    @Override
    public void showLoadingLayout() {
        mLoadingPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingLayout() {
        mLoadingPb.setVisibility(View.GONE);
    }

    public abstract BasePresenter getPresenter();

    public abstract void getViews();
    public abstract void bindData();
    public abstract void setListeners();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null!=getPresenter()) {
            getPresenter().onDestroy();
        }
    }
}
