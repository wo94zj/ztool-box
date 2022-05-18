package com.ztool.box.resp;

/**
 * @Author zhaoj
 *
 */
public interface UnifyCode<T extends Enum> {

    T get();

    int code();

    String msg();
}
