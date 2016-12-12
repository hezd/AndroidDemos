package com.hezd.http.core;

/**
 * 网络请求
 * Created by hezd on 2016/11/30.
 */

public interface IRequestHandler<T> {
    /**
     * Get请求
     * @param url
     * @param params
     * @param listner
     */
    void sendGet(String url, HttpRequest params, Callback<T> listner);

    /**
     * Post请求
     * @param url
     * @param params
     * @param listner
     */
    void sendPost(String url, HttpRequest params, Callback<T> listner);
}
