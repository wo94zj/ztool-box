package com.ztool.box.resp;

/**
 * @Author zhaoj
 */
public class UnifyRespHelper {

    public static UnifyResp success() {
        return UnifyResp.newBuilder().build();
    }

    public static <T> UnifyResp success(T data) {
        return UnifyResp.newBuilder().withData(data).build();
    }

    public static UnifyResp failed(UnifyCode uc) {
        return UnifyResp.newBuilder().withCode(uc.code()).withMsg(uc.msg()).build();
    }

    public static UnifyResp failed(int code, String msg) {
        return UnifyResp.newBuilder().withCode(code).withMsg(msg).build();
    }
}
