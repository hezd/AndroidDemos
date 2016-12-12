package com.hezd.http.core;

import com.google.gson.Gson;

/**
 * Created by hezd on 2016/12/8.
 */

public class JsonSerializator implements IGenericSerializator {
    private final Gson mGson = new Gson();

    public JsonSerializator() {
    }

    @Override
    public <T> T transfrom(String result, Class<T> classOfT) {
        return mGson.fromJson(result,classOfT);
    }
}
