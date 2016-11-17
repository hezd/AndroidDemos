package com.hezd.mvpexample.activity;

/**
 * activity相当于mvp中的view，它的接口定义了主要的业务逻辑。
 * 具体v与m的教育通过p来实现。
 *
 * Created by hezd on 2016/11/7.
 */

public interface ILoginView<T> {
    void login(T t);
}
