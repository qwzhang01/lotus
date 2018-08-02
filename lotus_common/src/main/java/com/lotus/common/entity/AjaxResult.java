package com.lotus.common.entity;

public class AjaxResult {
    private String message;
    private int errorCode = 0; // 0: normal , >=1 : error
    private Object data;

    public static AjaxResult success(String message) {
        AjaxResult result = new AjaxResult();
        result.setErrorCode(0);
        result.setMessage(message);
        return result;
    }

    public static AjaxResult success(String message, Object data) {
        AjaxResult result = new AjaxResult();
        result.setErrorCode(0);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static AjaxResult error(String message) {
        AjaxResult result = new AjaxResult();
        result.setErrorCode(1);
        result.setMessage(message);
        return result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
