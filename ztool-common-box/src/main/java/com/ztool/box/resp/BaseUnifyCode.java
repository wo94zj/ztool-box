package com.ztool.box.resp;

/**
 * @Author zhaoj
 *
 */
public enum BaseUnifyCode implements UnifyCode<BaseUnifyCode> {

    SUCCESS(0, "OK"),

    FAILED(201, "参数不合法"),   // 请求正常，但未能按客户端要求正确返回结果
    BUSY(202, "服务器繁忙"),

    ERROR_400(400, "接口请求参数不匹配"),    // 客户端请求参数问题（缺失、格式不正确等）
    ERROR_401(401, "接口访问未授权"),
    ERROR_403(403, "接口禁止访问"),
    ERROR_404(404, "接口请求地址错误"),
    ERROR_405(405, "接口方法错误"),
    ERROR_408(408, "请求超时"),
    ERROR_415(415, "不支持的媒体类型"),

    ERROR_500(500, "服务内部异常"),
    ERROR_502(502, "服务网关错误"),
    ERROR_503(503, "服务暂不可用"),
    ERROR_504(504, "服务网关超时"),
    ;


    private int code;

    private String msg;

    BaseUnifyCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public BaseUnifyCode get() {
        return this;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
