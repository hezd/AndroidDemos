package com.hezd.http.core;

/**
 * Created by hezd on 2016/12/1.
 */

public abstract class HttpRequestFactory {
    /**
     * 获取http请求manager
     * @return
     */
    public abstract IRequestHandler getRequestHandler();

}
