package com.hezd.http.core;

import java.util.Map;

/**
 * 请求封装
 * Created by hezd on 2016/12/9.
 */

public class HttpRequest {
    private Map<String,Object> params;
    private Map<String,String> headers;
    private String tag;
    private int flag;

    @Override
    public String toString() {
        return "HttpRequest{" +
                "params=" + params +
                ", headers=" + headers +
                ", tag='" + tag + '\'' +
                ", flag=" + flag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpRequest that = (HttpRequest) o;

        if (flag != that.flag) return false;
        if (params != null ? !params.equals(that.params) : that.params != null) return false;
        if (headers != null ? !headers.equals(that.headers) : that.headers != null) return false;
        return tag != null ? tag.equals(that.tag) : that.tag == null;

    }

    @Override
    public int hashCode() {
        int result = params != null ? params.hashCode() : 0;
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + flag;
        return result;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
