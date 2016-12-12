package com.hezd.http.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by hezd on 2016/12/8.
 */

public abstract class GenericCallback<T> implements Callback<T> {
    IGenericSerializator mGenericSerializator;
    public GenericCallback(IGenericSerializator genericSerializator) {
        this.mGenericSerializator = genericSerializator;
    }

    @Override
    public T parseNetWorkResponse(String result) {
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Class<T> clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        if(clazz == String.class)
            return (T) result;

        return mGenericSerializator.transfrom(result,clazz);
    }
}
