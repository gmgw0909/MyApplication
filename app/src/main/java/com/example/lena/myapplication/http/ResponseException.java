package com.example.lena.myapplication.http;

/**
 * 创建者：  LeeBoo
 * 创建时间：2016/9/19 17:52
 */
public class ResponseException extends RuntimeException {
    private String code;

    public ResponseException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
