package com.hezd.http.impl;

import com.hezd.http.core.HttpRequestFactory;
import com.hezd.http.core.IRequestHandler;

import okhttp3.OkHttpClient;

/**
 * Created by hezd on 2016/12/1.
 */

public class MyRequestFactory extends HttpRequestFactory {

    private volatile static MyRequestFactory sInstance = null;
    private OkHttpClient okHttpClient = null;

    private MyRequestFactory() {
        okHttpClient = new OkHttpClient();
    }

    public static HttpRequestFactory getInstance() {
        if(sInstance==null){
            synchronized (MyRequestFactory.class) {
                if(sInstance == null){
                    sInstance = new MyRequestFactory();
                }
            }
        }
        return sInstance;
    }

    @Override
    public IRequestHandler getRequestHandler() {
        return OkHttpHandler.getInstance();
    }

}
