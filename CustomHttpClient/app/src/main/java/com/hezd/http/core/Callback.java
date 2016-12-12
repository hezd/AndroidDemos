package com.hezd.http.core;

/**
 * http请求回调接口
 * Created by hezd on 2016/11/30.
 */
public interface Callback<T> {

    void onSuccess(T t);

    void onError(int errorcode,String errorMsg);

    void onNetWorkFailed(String msg);

    T parseNetWorkResponse(String resultStr);
}
