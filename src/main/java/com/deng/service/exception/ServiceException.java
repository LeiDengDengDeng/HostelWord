package com.deng.service.exception;

/**
 * Created by deng on 2017/3/8.
 * 自定义exception
 */
public class ServiceException extends Exception implements ExceptionEnum {
    private int code; // 错误码
    private String message; // 错误消息

    public ServiceException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getErrorMessage() {
        return message;
    }
}
