package com.hezd.http.impl;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.hezd.http.bean.ErrorBean;
import com.hezd.http.core.Callback;
import com.hezd.http.core.HttpRequest;
import com.hezd.http.core.IRequestHandler;

import org.simple.eventbus.EventBus;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hezd on 2016/12/1.
 */

public class OkHttpHandler implements IRequestHandler {

    public static final int SUCECCESS_CODE = 200;

    private String mTag;
    private static OkHttpHandler sInstance = null;
    private OkHttpClient mHttpClient = null;
    private Handler mHandler = null;

    public OkHttpHandler() {
        mHttpClient = new OkHttpClient();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpHandler getInstance() {
        if (sInstance == null) {
            synchronized (OkHttpHandler.class) {
                if (sInstance == null) {
                    sInstance = new OkHttpHandler();
                }
            }
        }
        return sInstance;
    }

    public void tag(String tag) {
        this.mTag = tag;
    }

    @Override
    public void sendGet(String url, HttpRequest params, final Callback listner) {
        Request request = new Request.Builder().url(url).build();
        final String tag = params.getTag();
        if(params!=null){
            Map param = params.getParams();
            // append params to url
            // add header to request
        }
        mHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                final String message = e.getMessage();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(listner!=null)
                             listner.onNetWorkFailed(message);

                        if(tag!=null) {
                            ErrorBean errorBean = new ErrorBean();
                            errorBean.setMessage(message);
                            EventBus.getDefault().post(errorBean,tag);
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final int code = response.code();
                final String result = response.body().string();
                final String message = response.message();
//                final Object obj = listner.parseNetWorkResponse(result);
                if (code == SUCECCESS_CODE) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //                                String result = response.body().string();
//                        if(listner!=null)
//                            listner.onSuccess(obj);

                        if(!TextUtils.isEmpty(tag)) {
                            EventBus.getDefault().post(result,tag);
                        }
                        }
                    });

                } else {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listner.onError(code, message);
                        }
                    });

                }
            }
        });
    }

    @Override
    public void sendPost(String url, HttpRequest params, Callback response) {

    }

}
