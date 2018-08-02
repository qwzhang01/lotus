package com.lotus.api.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class LotusResult implements Serializable {
    private static final long serialVersionUID = -3770689023315736370L;

    @ApiModelProperty("结果标识: 0返回提示语言,没有实体,1返回成功实体,-1,返回失败提示语,没有实体")
    private int code;
    @ApiModelProperty("提示语")
    private String message;
    @ApiModelProperty("结果数据集")
    private Object data;

    public LotusResult() {
    }

    private LotusResult(String message) {
        this(1, message, null);
    }

    private LotusResult(Object data) {
        this(1, "获取成功", data);
    }

    private LotusResult(int code, String message) {
        this(code, message, null);
    }

    private LotusResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static LotusResult success(Object data) {
        return new LotusResult(data);
    }

    public static LotusResult success(Object data, String message) {
        return new LotusResult(1, message, data);
    }

    public static LotusResult info(String message) {
        return new LotusResult(0, message);
    }

    public static LotusResult error(String message) {
        return new LotusResult(-1, message);
    }

    public static LotusResult error(String message, Object excetion) {
        return new LotusResult(-1, message, excetion);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}