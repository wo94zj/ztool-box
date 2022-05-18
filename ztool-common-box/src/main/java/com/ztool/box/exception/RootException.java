package com.ztool.box.exception;

import lombok.Data;

/**
 * @Author zhaoj
 *
 */
@Data
public class RootException extends RuntimeException {

    private int code;

    public RootException() {
        super();
        this.code = -1;
    }

    public RootException(int code, String message) {
        super(message);
        this.code = code;
    }
}
