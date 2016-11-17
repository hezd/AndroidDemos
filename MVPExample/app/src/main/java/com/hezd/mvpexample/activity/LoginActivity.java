package com.hezd.mvpexample.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hezd.mvpexample.R;
import com.hezd.mvpexample.presenter.BasePresenter;
import com.hezd.mvpexample.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity implements View.OnClickListener ,ILoginView<String>{

    private Button mLoginBtn;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
    }

    @Override
    public void getViews() {
        mLoginBtn = (Button) findViewById(R.id.btn_login);
    }

    @Override
    public void bindData() {

    }

    @Override
    public void setListeners() {
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                showLoadingLayout();
                mLoginPresenter.login();
                break;
        }
    }

    @Override
    public BasePresenter getPresenter() {
        if(mLoginPresenter==null) {
            mLoginPresenter = new LoginPresenter(this,this,this);
        }
        return mLoginPresenter;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.onDestroy();
    }

    @Override
    public void login(String msg) {
        showToast(msg);
    }
}
