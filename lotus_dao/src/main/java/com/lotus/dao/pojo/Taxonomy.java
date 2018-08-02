package com.lotus.dao.pojo;

import java.io.Serializable;

public class Taxonomy implements Serializable {
    private Integer id;

    private String title;

    private String type;

    private String appColumn;

    private String remark;

    private Integer orderNumber;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getAppColumn() {
        return appColumn;
    }

    public void setAppColumn(String appColumn) {
        this.appColumn = appColumn == null ? null : appColumn.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}