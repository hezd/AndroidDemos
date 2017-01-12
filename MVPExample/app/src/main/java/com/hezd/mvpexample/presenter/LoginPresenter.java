package com.hezd.mvpexample.presenter;

import android.app.DownloadManager;
import android.content.Context;

import com.hezd.mvpexample.activity.IBaseView;
import com.hezd.mvpexample.activity.ILoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 主导器负责m与v交互
 * Created by hezd on 2016/11/7.
 */

public class LoginPresenter extends BasePresenter<IBaseView> {
    public static final String LOGIN_TAG = "login_tag";
    private final ILoginView mLoginView;

    public LoginPresenter(Context context, IBaseView callbackView, ILoginView loginView) {
        super(context,callbackView);
        this.mLoginView = loginView;
    }

    public void login() {
        // 模拟延时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    EventBus.getDefault().post("登录成功");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        DownloadManager
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLogin(String msg) {
        mLoginView.login(msg);
        mViewCallBack.hideLoadingLayout();
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        mViewCallBack = null;
        EventBus.getDefault().unregister(this);
    }
}
