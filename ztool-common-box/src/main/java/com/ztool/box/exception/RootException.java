package com.ztool.box.exception;

import com.ztool.box.resp.UnifyCode;
import lombok.Data;

import java.lang.reflect.Array;

/**
 * @Author zhaoj
 *
 */
@Data
public class RootException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;

    public RootException() {
        super();
        this.code = -1;
    }

    public RootException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RootException(UnifyCode unifyCode, Object... args) {
        this.code = unifyCode.code();
        if (args != null && Array.getLength(args) > 0) {
            this.message = String.format(unifyCode.msg(), args);
        } else {
            this.message = unifyCode.msg();
        }
    }
}
