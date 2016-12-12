package com.hezd.http.core;

/**
 * Created by hezd on 2016/12/8.
 */

public interface IGenericSerializator {
    <T> T transfrom(String response,Class<T> classOfT);
}
