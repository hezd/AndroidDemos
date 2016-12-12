package com.hezd.http.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hezd.http.R;
import com.hezd.http.bean.ErrorBean;
import com.hezd.http.core.HttpRequest;
import com.hezd.http.core.IRequestHandler;
import com.hezd.http.impl.MyRequestFactory;
import com.hezd.http.impl.MyRequestInterface;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String MY_BLOG_TAG = "my_blog_tag";
    private Button mPostBtn;
    private Button mGetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
        getViews();
        setViews();
        setListeners();
    }

    private void requestGet() {
        IRequestHandler requestHandler = MyRequestFactory.getInstance().getRequestHandler();
        HttpRequest request = new HttpRequest();
        request.setTag(MY_BLOG_TAG);
        requestHandler.sendGet(MyRequestInterface.Get.MY_BLOG_URL, request, null);
    }

    private void requestPost() {
        IRequestHandler requestHandler = MyRequestFactory.getInstance().getRequestHandler();
        HttpRequest builder = new HttpRequest();
//        builder.setTag();
    }

    @Subscriber(tag = MY_BLOG_TAG)
    public void onSucess(Object result) {
        if(result!=null)
        Log.d("hezd","onSucess="+String.valueOf(result));
    }

    @Subscriber(tag = MY_BLOG_TAG)
    public void onError(ErrorBean bean) {
        if(bean!=null) {
            Log.d("hezd","onError="+bean.getMessage());
        }
    }

    private void getViews() {
        mGetBtn = (Button) findViewById(R.id.btn_http_get);
        mPostBtn = (Button) findViewById(R.id.btn_post);
    }

    private void setViews() {

    }

    private void setListeners() {
        mGetBtn.setOnClickListener(this);
        mPostBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_http_get:
                requestGet();
                break;
            case R.id.btn_post:
                requestPost();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
