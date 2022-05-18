package com.ztool.box.resp;

import lombok.Data;

/**
 * @Author zhaoj
 *
 */
@Data
public class UnifyResp<T> {

    private int code = 0;

    private String msg = "OK";

    private T data;


    public UnifyResp() {
    }

    public UnifyResp(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static class Builder<T> {

        private int code = 0;

        private String msg = "OK";

        private T data;

        public Builder withCode(int code) {
            this.code = code;
            return this;
        }

        public Builder withMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder withUnifyCode(UnifyCode uc) {
            this.code = uc.code();
            this.msg = uc.msg();
            return this;
        }

        public Builder withData(T data) {
            this.data = data;
            return this;
        }

        public UnifyResp build() {
            return new UnifyResp(this.code, this.msg, this.data);
        }

    }
}
